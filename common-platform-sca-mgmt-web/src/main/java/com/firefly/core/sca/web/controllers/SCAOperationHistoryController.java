package com.firefly.core.sca.web.controllers;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.sca.core.services.SCAOperationHistoryService;
import com.firefly.core.sca.interfaces.dtos.SCAOperationHistoryDTO;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

/**
 * Controller for managing historical status changes/events for a given SCA Operation.
 */
@RestController
@RequestMapping("/api/v1/sca/operations/{operationId}/history")
public class SCAOperationHistoryController {

    @Autowired
    private SCAOperationHistoryService historyService;


    @GetMapping
    public Mono<ResponseEntity<PaginationResponse<SCAOperationHistoryDTO>>> getAllHistory(
            @PathVariable UUID operationId,
            @ParameterObject @ModelAttribute PaginationRequest paginationRequest
    ) {
        return historyService.findAllByOperationId(operationId, paginationRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<SCAOperationHistoryDTO>> createHistory(
            @PathVariable UUID operationId,
            @RequestBody SCAOperationHistoryDTO dto
    ) {
        return historyService.create(operationId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{historyId}")
    public Mono<ResponseEntity<SCAOperationHistoryDTO>> getHistory(
            @PathVariable UUID operationId,
            @PathVariable UUID historyId
    ) {
        return historyService.findById(operationId, historyId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{historyId}")
    public Mono<ResponseEntity<SCAOperationHistoryDTO>> updateHistory(
            @PathVariable UUID operationId,
            @PathVariable UUID historyId,
            @RequestBody SCAOperationHistoryDTO dto
    ) {
        return historyService.update(operationId, historyId, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{historyId}")
    public Mono<ResponseEntity<Void>> deleteHistory(
            @PathVariable UUID operationId,
            @PathVariable UUID historyId
    ) {
        return historyService.delete(operationId, historyId)
                .then(Mono.fromCallable(() -> ResponseEntity.noContent().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}