/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.firefly.core.sca.models.repositories;

import com.firefly.core.sca.models.entities.SCAChallenge;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface SCAChallengeRepository extends BaseRepository<SCAChallenge, UUID> {
    Flux<SCAChallenge> findAllByScaOperationId(UUID scaOperationId, Pageable pageable);

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
    Mono<SCAChallenge> findActiveChallengeForOperation(UUID scaOperationId);
    Mono<Long> countByScaOperationId(UUID scaOperationId);
}