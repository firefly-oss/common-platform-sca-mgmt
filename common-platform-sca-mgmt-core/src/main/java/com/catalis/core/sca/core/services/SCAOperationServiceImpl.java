package com.catalis.core.sca.core.services;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.sca.core.mappers.SCAOperationMapper;
import com.catalis.core.sca.interfaces.dtos.SCAOperationDTO;
import com.catalis.core.sca.interfaces.dtos.ValidationResultDTO;
import com.catalis.core.sca.interfaces.enums.SCAStatusEnum;
import com.catalis.core.sca.models.entities.SCAOperation;
import com.catalis.core.sca.models.repositories.SCAOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;

@Service
@Transactional
public class SCAOperationServiceImpl implements SCAOperationService {

    @Autowired
    private SCAChallengeService challengeService;


    @Autowired
    private SCAOperationRepository repository;

    @Autowired
    private SCAOperationMapper mapper;

    @Override
    public Mono<PaginationResponse<SCAOperationDTO>> filterAll(FilterRequest<SCAOperationDTO> filterRequest) {
        return FilterUtils.createFilter(
                        SCAOperation.class,
                        mapper::toDTO
                ).filter(filterRequest)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<SCAOperationDTO> create(SCAOperationDTO dto) {
        return Mono.just(dto)
                .map(mapper::toEntity)
                .flatMap(entity -> {
                    entity.setCreatedAt(LocalDateTime.now());
                    entity.setLastUpdated(LocalDateTime.now());
                    return repository.save(entity);
                })
                .map(mapper::toDTO)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<SCAOperationDTO> findById(Long operationId) {
        return repository.findById(operationId)
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.error(new RuntimeException("SCA Operation not found")))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<SCAOperationDTO> update(Long operationId, SCAOperationDTO dto) {
        return repository.findById(operationId)
                .flatMap(existing -> {
                    SCAOperation updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setId(operationId);
                    updatedEntity.setCreatedAt(existing.getCreatedAt());
                    updatedEntity.setLastUpdated(LocalDateTime.now());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.error(new RuntimeException("SCA Operation not found")))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Void> delete(Long operationId) {
        return repository.findById(operationId)
                .flatMap(repository::delete)
                .switchIfEmpty(Mono.error(new RuntimeException("SCA Operation not found")))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Void> triggerSCA(Long operationId) {

        return repository.findById(operationId)
                .flatMap(existing -> {
                    existing.setStatus(SCAStatusEnum.PENDING);
                    existing.setLastUpdated(LocalDateTime.now());
                    return repository.save(existing);
                })
                .switchIfEmpty(Mono.error(new RuntimeException("SCA Operation not found")))
                .then()
                .subscribeOn(Schedulers.boundedElastic());
    }

    /**
     *  1) We first find the SCA operation to ensure it's PENDING.
     *  2) We then find an "active" challenge for that operation (not used, not expired).
     *  3) We call challengeService.validateChallenge(operationId, challengeId, userCode).
     *  4) We update the operation status to VERIFIED or FAILED depending on the validation result.
     */
    @Override
    public Mono<ValidationResultDTO> validateSCA(Long operationId, String userCode) {
        return repository.findById(operationId)
                .flatMap(existingOperation -> {

                    // 1) Ensure operation is in a valid status for validation
                    if (existingOperation.getStatus() != SCAStatusEnum.PENDING) {
                        return Mono.just(new ValidationResultDTO(
                                false,
                                false,
                                "SCA Operation not in a valid state for validation"
                        ));
                    }

                    // 2) Find an active challenge for this operation (not used, not expired, etc.)
                    //    For example: challengeService might expose a method "findActiveChallengeForOperation(...)"
                    //    that returns a Mono<SCAChallenge> or Mono.empty() if none found.
                    return challengeService.findActiveChallengeForOperation(operationId)
                            .flatMap(activeChallenge ->
                                    // 3) Now validate the user code for that specific challenge
                                    challengeService.validateChallenge(operationId, activeChallenge.getId(), userCode)
                            )
                            .flatMap(validationResult -> {
                                // 4) Update the operation status based on whether code is valid
                                existingOperation.setStatus(
                                        validationResult.isSuccess() ? SCAStatusEnum.VERIFIED : SCAStatusEnum.FAILED
                                );
                                existingOperation.setLastUpdated(LocalDateTime.now());

                                // Save updates to SCAOperation in DB, then return the validation result
                                return repository.save(existingOperation)
                                        .thenReturn(validationResult);
                            })
                            .defaultIfEmpty(new ValidationResultDTO(
                                    false,
                                    false,
                                    "No active challenge found for this operation"
                            ));
                })
                .switchIfEmpty(Mono.error(new RuntimeException("SCA Operation not found")))
                .subscribeOn(Schedulers.boundedElastic());
    }
    
}