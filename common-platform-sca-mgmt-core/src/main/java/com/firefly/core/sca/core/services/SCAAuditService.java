package com.firefly.core.sca.core.services;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.sca.interfaces.dtos.SCAAuditDTO;
import reactor.core.publisher.Mono;
import java.util.UUID;

/**
 * Defines operations for logging or retrieving in-depth audit events
 * associated with an SCA Operation or Challenge.
 */
public interface SCAAuditService {
    Mono<PaginationResponse<SCAAuditDTO>> findAllByOperationId(UUID operationId, PaginationRequest paginationRequest);
    Mono<SCAAuditDTO> create(UUID operationId, SCAAuditDTO dto);
    Mono<SCAAuditDTO> findById(UUID operationId, UUID auditId);
    Mono<SCAAuditDTO> update(UUID operationId, UUID auditId, SCAAuditDTO dto);
    Mono<Void> delete(UUID operationId, UUID auditId);
}