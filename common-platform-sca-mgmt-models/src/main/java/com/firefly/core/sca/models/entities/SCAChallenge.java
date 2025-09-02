package com.firefly.core.sca.models.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Stores the one-time code or challenge tokens related to an SCA operation.
 */
@Data
@Table("sca_challenge")
public class SCAChallenge {

    @Id
    @Column("id")
    private UUID id;

    @Column("sca_operation_id")
    private UUID scaOperationId; // references sca_operation.id

    @Column("challenge_code")
    private String challengeCode; // the one-time code/OTP token

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("expires_at")
    private LocalDateTime expiresAt;

    @Column("used")
    private Boolean used; // flag indicating if the code was used
}
