package com.catalis.core.sca.interfaces.dtos;

import com.catalis.common.core.filters.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * DTO for an SCA Challenge (OTP code).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SCAChallengeDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @FilterableId
    private Long scaOperationId;

    private String challengeCode;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private Boolean used;
}

