package com.catalis.core.sca.interfaces.dtos;

import lombok.Data;

/**
 * A generic error response DTO for SCA operations,
 * providing a code, message, and optional details.
 */
@Data
public class SCAErrorDTO {

    /**
     * A short error code (e.g., "SCA-LOCKED", "SCA-INVALID").
     */
    private String errorCode;

    /**
     * A human-readable message describing the error.
     */
    private String errorMessage;

    /**
     * Additional details if needed (stack trace, fields, etc.).
     */
    private String errorDetails;
}
