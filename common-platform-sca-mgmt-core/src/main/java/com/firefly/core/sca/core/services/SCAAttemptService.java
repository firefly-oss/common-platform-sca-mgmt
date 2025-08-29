package com.firefly.core.sca.core.services;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.sca.interfaces.dtos.SCAAttemptDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Defines operations for tracking user attempts on a given SCAChallenge.
 */
public interface SCAAttemptService {
    Mono<PaginationResponse<SCAAttemptDTO>> findAllByChallengeId(Long operationId, Long challengeId, PaginationRequest paginationRequest);
    Mono<SCAAttemptDTO> create(Long operationId, Long challengeId, SCAAttemptDTO dto);
    Mono<SCAAttemptDTO> findById(Long operationId, Long challengeId, Long attemptId);
    Mono<SCAAttemptDTO> update(Long operationId, Long challengeId, Long attemptId, SCAAttemptDTO dto);
    Mono<Void> delete(Long operationId, Long challengeId, Long attemptId);
}