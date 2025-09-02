package com.firefly.core.sca.models.repositories;

import com.firefly.core.sca.models.entities.SCAAudit;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface SCAAuditRepository extends BaseRepository<SCAAudit, UUID> {
    Flux<SCAAudit> findAllByScaOperationId(UUID scaOperationId, Pageable pageable);
    Mono<Long> countByScaOperationId(UUID scaOperationId);
}
