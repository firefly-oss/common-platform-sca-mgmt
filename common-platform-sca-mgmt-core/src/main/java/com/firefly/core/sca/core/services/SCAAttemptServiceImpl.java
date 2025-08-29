package com.firefly.core.sca.core.services;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.core.sca.core.mappers.SCAAttemptMapper;
import com.firefly.core.sca.interfaces.dtos.SCAAttemptDTO;
import com.firefly.core.sca.models.entities.SCAAttempt;
import com.firefly.core.sca.models.repositories.SCAAttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class SCAAttemptServiceImpl implements SCAAttemptService {

    @Autowired
    private SCAAttemptRepository repository;

    @Autowired
    private SCAAttemptMapper mapper;

    @Override
    public Mono<PaginationResponse<SCAAttemptDTO>> findAllByChallengeId(Long operationId, Long challengeId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findAllByScaChallengeId(challengeId, pageable),
                () -> repository.countByScaChallengeId(challengeId)
        );
    }

    @Override
    public Mono<SCAAttemptDTO> create(Long operationId, Long challengeId, SCAAttemptDTO dto) {
        dto.setScaChallengeId(challengeId);
        SCAAttempt entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<SCAAttemptDTO> findById(Long operationId, Long challengeId, Long attemptId) {
        return repository.findById(attemptId)
                .filter(attempt -> challengeId.equals(attempt.getScaChallengeId()))
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Attempt not found or does not belong to the challenge.")))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<SCAAttemptDTO> update(Long operationId, Long challengeId, Long attemptId, SCAAttemptDTO dto) {
        return repository.findById(attemptId)
                .filter(attempt -> challengeId.equals(attempt.getScaChallengeId()))
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Attempt not found or does not belong to the challenge.")))
                .flatMap(existingAttempt -> {
                    SCAAttempt updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setId(attemptId);
                    updatedEntity.setScaChallengeId(challengeId);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long operationId, Long challengeId, Long attemptId) {
        return repository.findById(attemptId)
                .filter(attempt -> challengeId.equals(attempt.getScaChallengeId()))
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Attempt not found or does not belong to the challenge.")))
                .flatMap(repository::delete);
    }
}
