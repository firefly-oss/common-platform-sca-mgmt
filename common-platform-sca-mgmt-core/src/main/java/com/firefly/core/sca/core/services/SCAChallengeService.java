package com.firefly.core.sca.core.services;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.sca.interfaces.dtos.SCAChallengeDTO;
import com.firefly.core.sca.interfaces.dtos.ValidationResultDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Defines operations for handling SCAChallenge records:
 * listing, creating, validating specific OTP challenges, etc.
 */
public interface SCAChallengeService {

    Mono<PaginationResponse<SCAChallengeDTO>> findAllByOperationId(Long operationId, PaginationRequest paginationRequest);
    Mono<SCAChallengeDTO> create(Long operationId, SCAChallengeDTO dto);
    Mono<SCAChallengeDTO> findById(Long operationId, Long challengeId);
    Mono<SCAChallengeDTO> update(Long operationId, Long challengeId, SCAChallengeDTO dto);
    Mono<Void> delete(Long operationId, Long challengeId);
    Mono<SCAChallengeDTO> findActiveChallengeForOperation(Long operationId);

    /**
     * Validate a single challenge with the given user code (OTP).
     */
    Mono<ValidationResultDTO> validateChallenge(Long operationId, Long challengeId, String userCode);
}