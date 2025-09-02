package com.firefly.core.sca.web.controllers;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.sca.core.services.SCAOperationService;
import com.firefly.core.sca.interfaces.dtos.SCAOperationDTO;
import com.firefly.core.sca.interfaces.dtos.ValidationResultDTO;
import jakarta.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.util.UUID;

/**
 * Controller responsible for managing SCA Operations (core entity).
 * Delegates to SCAOperationService for business logic.
 */
@RestController
@RequestMapping("/api/v1/sca/operations")
public class SCAOperationController {

    @Autowired
    private SCAOperationService operationService;

    // --- CRUD Endpoints ---

    @GetMapping
    public Mono<ResponseEntity<PaginationResponse<SCAOperationDTO>>> filterOperations(FilterRequest<SCAOperationDTO> filterRequest) {
        return operationService.filterAll(filterRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<SCAOperationDTO>> createOperation(@RequestBody SCAOperationDTO dto) {
        return operationService.create(dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{operationId}")
    public Mono<ResponseEntity<SCAOperationDTO>> getOperation(@PathVariable UUID operationId) {
        return operationService.findById(operationId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{operationId}")
    public Mono<ResponseEntity<SCAOperationDTO>> updateOperation(
            @PathVariable UUID operationId,
            @RequestBody SCAOperationDTO dto
    ) {
        return operationService.update(operationId, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{operationId}")
    public Mono<ResponseEntity<Void>> deleteOperation(@PathVariable UUID operationId) {
        return operationService.delete(operationId)
                .map(r -> ResponseEntity.noContent().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // --- SCA Flow Endpoints ---

    @PostMapping("/{operationId}/trigger")
    public Mono<ResponseEntity<Void>> triggerSCA(@PathVariable UUID operationId) {
        return operationService.triggerSCA(operationId)
                .map(r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/{operationId}/validate")
    public Mono<ResponseEntity<ValidationResultDTO>> validateSCA(
            @PathVariable UUID operationId,
            @RequestParam(name="userCode", required=false) String userCode
    ) {
        return operationService.validateSCA(operationId, userCode)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}

