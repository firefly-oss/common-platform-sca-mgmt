package com.firefly.core.sca.core.services;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.sca.interfaces.dtos.SCAOperationHistoryDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

/**
 * Defines operations for managing status/event history of an SCAOperation.
 */
public interface SCAOperationHistoryService {
    Mono<PaginationResponse<SCAOperationHistoryDTO>> findAllByOperationId(UUID operationId, PaginationRequest paginationRequest);
    Mono<SCAOperationHistoryDTO> create(UUID operationId, SCAOperationHistoryDTO dto);
    Mono<SCAOperationHistoryDTO> findById(UUID operationId, UUID historyId);
    Mono<SCAOperationHistoryDTO> update(UUID operationId, UUID historyId, SCAOperationHistoryDTO dto);
    Mono<Void> delete(UUID operationId, UUID historyId);
}