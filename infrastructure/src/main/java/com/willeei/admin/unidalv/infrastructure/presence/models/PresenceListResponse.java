package com.willeei.admin.unidalv.infrastructure.presence.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PresenceListResponse(
        @JsonProperty("id") String id,
        @JsonProperty("date") LocalDate date,
        @JsonProperty("type") String type,
        @JsonProperty("is_active") boolean active,
        @JsonProperty("teen_id") String teenId,
        @JsonProperty("created_at") String createdAt,
        @JsonProperty("updated_at") String updatedAtString
) {

}
