package com.firefly.core.sca.web.controllers;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.sca.core.services.SCAAuditService;
import com.firefly.core.sca.interfaces.dtos.SCAAuditDTO;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Controller for managing audit records for an SCA Operation.
 * Optionally, you can also have a challenge-level audit path if needed.
 */
@RestController
@RequestMapping("/api/sca/v1/operations/{operationId}/audit")
public class SCAAuditController {

    @Autowired
    private SCAAuditService auditService;

    @GetMapping
    public Mono<ResponseEntity<PaginationResponse<SCAAuditDTO>>> getAllAudit(
            @PathVariable Long operationId,
            @ParameterObject @ModelAttribute PaginationRequest paginationRequest
    ) {
        return auditService.findAllByOperationId(operationId, paginationRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<SCAAuditDTO>> createAuditEvent(
            @PathVariable Long operationId,
            @RequestBody SCAAuditDTO dto
    ) {
        return auditService.create(operationId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{auditId}")
    public Mono<ResponseEntity<SCAAuditDTO>> getAuditEvent(
            @PathVariable Long operationId,
            @PathVariable Long auditId
    ) {
        return auditService.findById(operationId, auditId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{auditId}")
    public Mono<ResponseEntity<SCAAuditDTO>> updateAuditEvent(
            @PathVariable Long operationId,
            @PathVariable Long auditId,
            @RequestBody SCAAuditDTO dto
    ) {
        return auditService.update(operationId, auditId, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{auditId}")
    public Mono<ResponseEntity<Void>> deleteAuditEvent(
            @PathVariable Long operationId,
            @PathVariable Long auditId
    ) {
        return auditService.delete(operationId, auditId)
                .then(Mono.fromCallable(() -> ResponseEntity.noContent().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}