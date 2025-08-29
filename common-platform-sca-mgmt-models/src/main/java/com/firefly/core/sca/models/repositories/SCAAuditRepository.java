package com.firefly.core.sca.models.repositories;

import com.firefly.core.sca.models.entities.SCAAudit;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SCAAuditRepository extends BaseRepository<SCAAudit, Long> {
    Flux<SCAAudit> findAllByScaOperationId(Long scaOperationId, Pageable pageable);
    Mono<Long> countByScaOperationId(Long scaOperationId);
}
