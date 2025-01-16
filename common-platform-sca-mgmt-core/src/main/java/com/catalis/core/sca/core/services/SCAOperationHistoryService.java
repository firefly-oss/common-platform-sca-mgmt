package com.catalis.core.sca.core.services;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.sca.interfaces.dtos.SCAOperationHistoryDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Defines operations for managing status/event history of an SCAOperation.
 */
public interface SCAOperationHistoryService {
    Mono<PaginationResponse<SCAOperationHistoryDTO>> findAllByOperationId(Long operationId, PaginationRequest paginationRequest);
    Mono<SCAOperationHistoryDTO> create(Long operationId, SCAOperationHistoryDTO dto);
    Mono<SCAOperationHistoryDTO> findById(Long operationId, Long historyId);
    Mono<SCAOperationHistoryDTO> update(Long operationId, Long historyId, SCAOperationHistoryDTO dto);
    Mono<Void> delete(Long operationId, Long historyId);
}