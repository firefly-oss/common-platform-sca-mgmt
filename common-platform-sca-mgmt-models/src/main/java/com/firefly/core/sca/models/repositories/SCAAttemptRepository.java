package com.firefly.core.sca.models.repositories;

import com.firefly.core.sca.models.entities.SCAAttempt;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface SCAAttemptRepository extends BaseRepository<SCAAttempt, UUID> {
    Flux<SCAAttempt> findAllByScaChallengeId(UUID scaChallengeId, Pageable pageable);
    Mono<Long> countByScaChallengeId(UUID scaChallengeId);
}
