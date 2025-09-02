package com.firefly.core.sca.interfaces.dtos;

import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for an SCA Challenge (OTP code).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SCAChallengeDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @FilterableId
    private UUID scaOperationId;

    private String challengeCode;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private Boolean used;
}

