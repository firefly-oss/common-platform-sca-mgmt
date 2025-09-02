package com.firefly.core.sca.models.repositories;

import com.firefly.core.sca.models.entities.SCAOperationHistory;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface SCAOperationHistoryRepository extends BaseRepository<SCAOperationHistory, UUID> {
    Flux<SCAOperationHistory> findAllByScaOperationId(UUID scaOperationId, Pageable pageable);
    Mono<Long> countByScaOperationId(UUID scaOperationId);
}
