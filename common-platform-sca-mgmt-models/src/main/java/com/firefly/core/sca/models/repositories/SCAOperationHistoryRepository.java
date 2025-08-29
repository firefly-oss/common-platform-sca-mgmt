package com.firefly.core.sca.models.repositories;

import com.firefly.core.sca.models.entities.SCAOperationHistory;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SCAOperationHistoryRepository extends BaseRepository<SCAOperationHistory, Long> {
    Flux<SCAOperationHistory> findAllByScaOperationId(Long scaOperationId, Pageable pageable);
    Mono<Long> countByScaOperationId(Long scaOperationId);
}
