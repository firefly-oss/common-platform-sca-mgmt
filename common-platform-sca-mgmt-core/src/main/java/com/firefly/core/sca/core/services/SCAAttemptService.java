package com.firefly.core.sca.core.services;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.sca.interfaces.dtos.SCAAttemptDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

/**
 * Defines operations for tracking user attempts on a given SCAChallenge.
 */
public interface SCAAttemptService {
    Mono<PaginationResponse<SCAAttemptDTO>> findAllByChallengeId(UUID operationId, UUID challengeId, PaginationRequest paginationRequest);
    Mono<SCAAttemptDTO> create(UUID operationId, UUID challengeId, SCAAttemptDTO dto);
    Mono<SCAAttemptDTO> findById(UUID operationId, UUID challengeId, UUID attemptId);
    Mono<SCAAttemptDTO> update(UUID operationId, UUID challengeId, UUID attemptId, SCAAttemptDTO dto);
    Mono<Void> delete(UUID operationId, UUID challengeId, UUID attemptId);
}