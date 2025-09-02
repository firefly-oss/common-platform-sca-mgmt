package com.firefly.core.sca.core.services;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.sca.interfaces.dtos.SCAOperationDTO;
import com.firefly.core.sca.interfaces.dtos.ValidationResultDTO;
import reactor.core.publisher.Mono;
import java.util.UUID;

/**
 * Defines operations for managing the core SCAOperation entity,
 * including creation, update, trigger, and validate flows.
 */
public interface SCAOperationService {

    // --- CRUD Methods ---
    Mono<PaginationResponse<SCAOperationDTO>> filterAll(FilterRequest<SCAOperationDTO> filterRequest);
    Mono<SCAOperationDTO> create(SCAOperationDTO dto);
    Mono<SCAOperationDTO> findById(UUID operationId);
    Mono<SCAOperationDTO> update(UUID operationId, SCAOperationDTO dto);
    Mono<Void> delete(UUID operationId);

    // --- SCA Flow Methods ---
    /**
     * Trigger the SCA operation: generate challenges, send OTP, set status to PENDING, etc.
     */
    Mono<Void> triggerSCA(UUID operationId);

    /**
     * Validate the entire SCA operation, possibly with a user code (OTP).
     * Returns a ValidationResultDTO indicating success/failure/locked.
     */
    Mono<ValidationResultDTO> validateSCA(UUID operationId, String userCode);
}