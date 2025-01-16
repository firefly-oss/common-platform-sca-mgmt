package com.catalis.core.sca.web.controllers;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.sca.core.services.SCAAttemptService;
import com.catalis.core.sca.interfaces.dtos.SCAAttemptDTO;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Controller for managing SCA Attempts within a specific challenge (OTP attempts).
 */
@RestController
@RequestMapping("/api/v1/sca/operations/{operationId}/challenges/{challengeId}/attempts")
public class SCAAttemptController {

    @Autowired
    private SCAAttemptService attemptService;

    @GetMapping
    public Mono<ResponseEntity<PaginationResponse<SCAAttemptDTO>>> getAllAttempts(
            @PathVariable Long operationId,
            @PathVariable Long challengeId,
            @ParameterObject @ModelAttribute PaginationRequest paginationRequest
            ) {
        return attemptService.findAllByChallengeId(operationId, challengeId, paginationRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<SCAAttemptDTO>> createAttempt(
            @PathVariable Long operationId,
            @PathVariable Long challengeId,
            @RequestBody SCAAttemptDTO dto
    ) {
        return attemptService.create(operationId, challengeId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{attemptId}")
    public Mono<ResponseEntity<SCAAttemptDTO>> getAttempt(
            @PathVariable Long operationId,
            @PathVariable Long challengeId,
            @PathVariable Long attemptId
    ) {
        return attemptService.findById(operationId, challengeId, attemptId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{attemptId}")
    public Mono<ResponseEntity<SCAAttemptDTO>> updateAttempt(
            @PathVariable Long operationId,
            @PathVariable Long challengeId,
            @PathVariable Long attemptId,
            @RequestBody SCAAttemptDTO dto
    ) {
        return attemptService.update(operationId, challengeId, attemptId, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{attemptId}")
    public Mono<ResponseEntity<Void>> deleteAttempt(
            @PathVariable Long operationId,
            @PathVariable Long challengeId,
            @PathVariable Long attemptId
    ) {
        return attemptService.delete(operationId, challengeId, attemptId)
                .map(r -> ResponseEntity.noContent().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}