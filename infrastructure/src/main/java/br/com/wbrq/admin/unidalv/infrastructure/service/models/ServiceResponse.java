package br.com.wbrq.admin.unidalv.infrastructure.service.models;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ServiceResponse(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name,
        @JsonProperty("point") int point,
        @JsonProperty("is_active") boolean active,
        @JsonProperty("created_at") Instant createdAt,
        @JsonProperty("updated_at") Instant updatedAt,
        @JsonProperty("deleted_at") Instant deletedAt) {

}
