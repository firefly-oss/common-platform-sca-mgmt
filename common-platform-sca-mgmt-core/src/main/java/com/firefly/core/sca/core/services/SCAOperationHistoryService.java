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


package com.firefly.core.sca.core.services;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.sca.interfaces.dtos.SCAOperationHistoryDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

/**
 * Defines operations for managing status/event history of an SCAOperation.
 */
public interface SCAOperationHistoryService {
    Mono<PaginationResponse<SCAOperationHistoryDTO>> findAllByOperationId(UUID operationId, PaginationRequest paginationRequest);
    Mono<SCAOperationHistoryDTO> create(UUID operationId, SCAOperationHistoryDTO dto);
    Mono<SCAOperationHistoryDTO> findById(UUID operationId, UUID historyId);
    Mono<SCAOperationHistoryDTO> update(UUID operationId, UUID historyId, SCAOperationHistoryDTO dto);
    Mono<Void> delete(UUID operationId, UUID historyId);
}