package com.firefly.core.sca.models.repositories;

import com.firefly.core.sca.models.entities.SCAChallenge;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SCAChallengeRepository extends BaseRepository<SCAChallenge, Long> {
    Flux<SCAChallenge> findAllByScaOperationId(Long scaOperationId, Pageable pageable);

    /**
     * Finds one unexpired, unused challenge for the given operation ID.
     */
    @Query("""
        SELECT *
          FROM sca_challenge
         WHERE sca_operation_id = :operationId
           AND used = FALSE
           AND expires_at > CURRENT_TIMESTAMP
         LIMIT 1
    """)
    Mono<SCAChallenge> findActiveChallengeForOperation(Long scaOperationId);
    Mono<Long> countByScaOperationId(Long scaOperationId);
}