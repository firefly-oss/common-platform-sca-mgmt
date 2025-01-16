package com.catalis.core.sca.models.repositories;

import com.catalis.core.sca.models.entities.SCAAttempt;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SCAAttemptRepository extends BaseRepository<SCAAttempt, Long> {
    Flux<SCAAttempt> findAllByScaChallengeId(Long scaChallengeId, Pageable pageable);
    Mono<Long> countByScaChallengeId(Long scaChallengeId);
}
