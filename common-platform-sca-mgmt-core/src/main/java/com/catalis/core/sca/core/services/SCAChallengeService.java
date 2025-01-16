package com.catalis.core.sca.core.services;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.sca.interfaces.dtos.SCAChallengeDTO;
import com.catalis.core.sca.interfaces.dtos.ValidationResultDTO;
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