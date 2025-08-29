package com.firefly.core.sca.interfaces.dtos;

import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * DTO capturing an attempt to input a challenge code.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SCAAttemptDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @FilterableId
    private Long scaChallengeId;

    private String attemptValue;
    private LocalDateTime attemptedAt;
    private Boolean success;
    private String ipAddress;
}