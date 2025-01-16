package com.catalis.core.sca.web.controllers;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.sca.core.services.SCAOperationHistoryService;
import com.catalis.core.sca.interfaces.dtos.SCAOperationHistoryDTO;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
            @PathVariable Long operationId,
            @ParameterObject @ModelAttribute PaginationRequest paginationRequest
    ) {
        return historyService.findAllByOperationId(operationId, paginationRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<SCAOperationHistoryDTO>> createHistory(
            @PathVariable Long operationId,
            @RequestBody SCAOperationHistoryDTO dto
    ) {
        return historyService.create(operationId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{historyId}")
    public Mono<ResponseEntity<SCAOperationHistoryDTO>> getHistory(
            @PathVariable Long operationId,
            @PathVariable Long historyId
    ) {
        return historyService.findById(operationId, historyId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{historyId}")
    public Mono<ResponseEntity<SCAOperationHistoryDTO>> updateHistory(
            @PathVariable Long operationId,
            @PathVariable Long historyId,
            @RequestBody SCAOperationHistoryDTO dto
    ) {
        return historyService.update(operationId, historyId, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{historyId}")
    public Mono<ResponseEntity<Void>> deleteHistory(
            @PathVariable Long operationId,
            @PathVariable Long historyId
    ) {
        return historyService.delete(operationId, historyId)
                .then(Mono.fromCallable(() -> ResponseEntity.noContent().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}