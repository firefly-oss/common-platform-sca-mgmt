package com.catalis.core.sca.web.controllers;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.sca.core.services.SCAChallengeService;
import com.catalis.core.sca.interfaces.dtos.SCAChallengeDTO;
import com.catalis.core.sca.interfaces.dtos.ValidationResultDTO;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/sca/operations/{operationId}/challenges")
public class SCAChallengeController {

    @Autowired
    private SCAChallengeService challengeService;

    @GetMapping
    public Mono<ResponseEntity<PaginationResponse<SCAChallengeDTO>>> getAllChallenges(
            @PathVariable Long operationId,
            @ParameterObject @ModelAttribute PaginationRequest paginationRequest
    ) {
        return challengeService.findAllByOperationId(operationId, paginationRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @PostMapping
    public Mono<ResponseEntity<SCAChallengeDTO>> createChallenge(
            @PathVariable Long operationId,
            @RequestBody SCAChallengeDTO dto
    ) {
        return challengeService.create(operationId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{challengeId}")
    public Mono<ResponseEntity<SCAChallengeDTO>> getChallenge(
            @PathVariable Long operationId,
            @PathVariable Long challengeId
    ) {
        return challengeService.findById(operationId, challengeId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{challengeId}")
    public Mono<ResponseEntity<SCAChallengeDTO>> updateChallenge(
            @PathVariable Long operationId,
            @PathVariable Long challengeId,
            @RequestBody SCAChallengeDTO dto
    ) {
        return challengeService.update(operationId, challengeId, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{challengeId}")
    public Mono<ResponseEntity<Void>> deleteChallenge(
            @PathVariable Long operationId,
            @PathVariable Long challengeId
    ) {
        return challengeService.delete(operationId, challengeId)
                .map(r -> ResponseEntity.noContent().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // --- Validate a Single Challenge ---
    @PostMapping("/{challengeId}/validate")
    public Mono<ResponseEntity<String>> validateChallenge(
            @PathVariable Long operationId,
            @PathVariable Long challengeId,
            @RequestParam String userCode
    ) {
        return challengeService.validateChallenge(operationId, challengeId, userCode)
                .flatMap((ValidationResultDTO result) -> {
                    if (result.isLockedOrFailed()) {
                        return Mono.just(ResponseEntity.status(423).body("Challenge locked/failed"));
                    } else if (!result.isSuccess()) {
                        return Mono.just(ResponseEntity.status(403).body("Incorrect code"));
                    } else {
                        return Mono.just(ResponseEntity.ok("Challenge Validated"));
                    }
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}