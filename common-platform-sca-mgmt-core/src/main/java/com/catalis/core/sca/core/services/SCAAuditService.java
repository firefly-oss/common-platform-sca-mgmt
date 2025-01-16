package com.catalis.core.sca.core.services;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.sca.interfaces.dtos.SCAAuditDTO;
import reactor.core.publisher.Mono;

/**
 * Defines operations for logging or retrieving in-depth audit events
 * associated with an SCA Operation or Challenge.
 */
public interface SCAAuditService {
    Mono<PaginationResponse<SCAAuditDTO>> findAllByOperationId(Long operationId, PaginationRequest paginationRequest);
    Mono<SCAAuditDTO> create(Long operationId, SCAAuditDTO dto);
    Mono<SCAAuditDTO> findById(Long operationId, Long auditId);
    Mono<SCAAuditDTO> update(Long operationId, Long auditId, SCAAuditDTO dto);
    Mono<Void> delete(Long operationId, Long auditId);
}