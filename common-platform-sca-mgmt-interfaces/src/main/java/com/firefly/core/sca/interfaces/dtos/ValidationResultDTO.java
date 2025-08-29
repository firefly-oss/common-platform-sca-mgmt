package com.firefly.core.sca.interfaces.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO indicating the outcome of an SCA validation,
 * such as checking an OTP code or finalizing an SCA operation.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ValidationResultDTO {

    /**
     * Indicates if the validation attempt was successful (true) or not (false).
     */
    private boolean success;

    /**
     * Indicates if the SCA operation or challenge is locked or failed
     * (e.g., max retries exceeded, status=FAILED/LOCKED).
     */
    private boolean lockedOrFailed;

    /**
     * An optional message describing the result.
     * Could be "SCA Verified", "Wrong Code", "Max Retries Exceeded", etc.
     */
    private String message;

}