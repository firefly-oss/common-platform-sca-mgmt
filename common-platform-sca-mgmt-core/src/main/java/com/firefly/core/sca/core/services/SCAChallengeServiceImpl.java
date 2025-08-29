package com.firefly.core.sca.core.services;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.core.sca.core.mappers.SCAChallengeMapper;
import com.firefly.core.sca.interfaces.dtos.SCAChallengeDTO;
import com.firefly.core.sca.interfaces.dtos.ValidationResultDTO;
import com.firefly.core.sca.models.entities.SCAChallenge;
import com.firefly.core.sca.models.repositories.SCAChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Transactional
public class SCAChallengeServiceImpl implements SCAChallengeService {

    @Autowired
    private SCAChallengeRepository repository;

    @Autowired
    private SCAChallengeMapper mapper;

    @Override
    public Mono<PaginationResponse<SCAChallengeDTO>> findAllByOperationId(Long operationId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findAllByScaOperationId(operationId, pageable),
                () -> repository.countByScaOperationId(operationId)
        );
    }

    @Override
    public Mono<SCAChallengeDTO> create(Long operationId, SCAChallengeDTO dto) {
        SCAChallenge entity = mapper.toEntity(dto);
        entity.setScaOperationId(operationId);
        return repository.save(entity).map(mapper::toDTO);
    }

    @Override
    public Mono<SCAChallengeDTO> findById(Long operationId, Long challengeId) {
        return repository.findById(challengeId)
                .filter(challenge -> challenge.getScaOperationId().equals(operationId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<SCAChallengeDTO> update(Long operationId, Long challengeId, SCAChallengeDTO dto) {
        return repository.findById(challengeId)
                .filter(challenge -> challenge.getScaOperationId().equals(operationId))
                .flatMap(existingChallenge -> {
                    SCAChallenge updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setId(challengeId);
                    updatedEntity.setScaOperationId(operationId);
                    return repository.save(updatedEntity);
                }).map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long operationId, Long challengeId) {
        return repository.findById(challengeId)
                .filter(challenge -> challenge.getScaOperationId().equals(operationId))
                .flatMap(repository::delete);
    }

    @Override
    public Mono<SCAChallengeDTO> findActiveChallengeForOperation(Long operationId) {
        return repository.findActiveChallengeForOperation(operationId)
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("No active challenge found for operation")));
    }

    @Override
    public Mono<ValidationResultDTO> validateChallenge(Long operationId, Long challengeId, String userCode) {
        return repository.findById(challengeId)
                .filter(challenge -> challenge.getScaOperationId().equals(operationId))
                .flatMap(challenge -> {
                    if (Boolean.TRUE.equals(challenge.getUsed())) {
                        return Mono.just(new ValidationResultDTO(false, true, "Challenge already used"));
                    }
                    if (!challenge.getChallengeCode().equals(userCode)) {
                        return Mono.just(new ValidationResultDTO(false, false, "Invalid challenge code"));
                    }
                    if (challenge.getExpiresAt().isBefore(LocalDateTime.now())) {
                        return Mono.just(new ValidationResultDTO(false, true, "Challenge has expired"));
                    }

                    challenge.setUsed(true);
                    return repository.save(challenge)
                            .thenReturn(new ValidationResultDTO(true, false, "Challenge successfully validated"));
                })
                .defaultIfEmpty(new ValidationResultDTO(false, true, "Challenge not found"));
    }
}
