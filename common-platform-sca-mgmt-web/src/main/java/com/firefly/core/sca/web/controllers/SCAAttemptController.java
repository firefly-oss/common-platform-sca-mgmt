package com.firefly.core.sca.web.controllers;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.sca.core.services.SCAAttemptService;
import com.firefly.core.sca.interfaces.dtos.SCAAttemptDTO;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

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
            @PathVariable UUID operationId,
            @PathVariable UUID challengeId,
            @ParameterObject @ModelAttribute PaginationRequest paginationRequest
            ) {
        return attemptService.findAllByChallengeId(operationId, challengeId, paginationRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<SCAAttemptDTO>> createAttempt(
            @PathVariable UUID operationId,
            @PathVariable UUID challengeId,
            @RequestBody SCAAttemptDTO dto
    ) {
        return attemptService.create(operationId, challengeId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{attemptId}")
    public Mono<ResponseEntity<SCAAttemptDTO>> getAttempt(
            @PathVariable UUID operationId,
            @PathVariable UUID challengeId,
            @PathVariable UUID attemptId
    ) {
        return attemptService.findById(operationId, challengeId, attemptId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{attemptId}")
    public Mono<ResponseEntity<SCAAttemptDTO>> updateAttempt(
            @PathVariable UUID operationId,
            @PathVariable UUID challengeId,
            @PathVariable UUID attemptId,
            @RequestBody SCAAttemptDTO dto
    ) {
        return attemptService.update(operationId, challengeId, attemptId, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{attemptId}")
    public Mono<ResponseEntity<Void>> deleteAttempt(
            @PathVariable UUID operationId,
            @PathVariable UUID challengeId,
            @PathVariable UUID attemptId
    ) {
        return attemptService.delete(operationId, challengeId, attemptId)
                .map(r -> ResponseEntity.noContent().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}