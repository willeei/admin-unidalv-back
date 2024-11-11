package br.com.wbrq.admin.unidalv.infrastructure.service.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ServiceListResponse(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name,
        @JsonProperty("point") int point,
        @JsonProperty("is_active") boolean active,
        @JsonProperty("created_at") String createdAt,
        @JsonProperty("updated_at") String updatedAt) {

}
