package com.firefly.core.sca.models.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Tracks each user attempt to enter a challenge code.
 */
@Data
@Table("sca_attempt")
public class SCAAttempt {

    @Id
    @Column("id")
    private UUID id;

    @Column("sca_challenge_id")
    private UUID scaChallengeId; // references sca_challenge.id

    @Column("attempt_value")
    private String attemptValue; // code the user entered

    @Column("attempted_at")
    private LocalDateTime attemptedAt; // or LocalDateTime

    @Column("success")
    private Boolean success; // whether the attempt was successful

    @Column("ip_address")
    private String ipAddress; // optional IP from which the attempt was made
}

