/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.firefly.core.sca.models.entities;

import com.firefly.core.sca.interfaces.enums.SCAEventTypeEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Logs deeper events or metadata for compliance/audit.
 */
@Data
@Table("sca_audit")
public class SCAAudit {

    @Id
    @Column("id")
    private UUID id;

    @Column("sca_operation_id")
    private UUID scaOperationId; // references sca_operation.id

    @Column("sca_challenge_id")
    private UUID scaChallengeId; // references sca_challenge.id (optional)

    @Column("party_id")
    private String partyId; // same as sca_operation, if relevant

    @Column("event_type")
    private SCAEventTypeEnum eventType; // e.g. "CREATED", "ATTEMPTED", "VERIFIED"

    @Column("event_time")
    private LocalDateTime eventTime;

    @Column("details")
    private String details; // metadata or JSON with extra info
}