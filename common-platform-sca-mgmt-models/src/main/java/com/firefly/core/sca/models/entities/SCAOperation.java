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

import com.firefly.core.sca.interfaces.enums.SCAOperationTypeEnum;
import com.firefly.core.sca.interfaces.enums.SCAStatusEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents the core SCA operation or "session" requiring strong customer auth.
 */
@Data
@Table("sca_operation")
public class SCAOperation {

    @Id
    @Column("id")
    private UUID id;

    @Column("reference_id")
    private String referenceId;

    @Column("operation_type")
    private SCAOperationTypeEnum operationType;

    @Column("party_id")
    private String partyId;

    @Column("status")
    private SCAStatusEnum status;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("expires_at")
    private LocalDateTime expiresAt;

    @Column("last_updated")
    private LocalDateTime lastUpdated;

    @Column("cancelled_at")
    private LocalDateTime cancelledAt;
}
