package com.willeei.admin.unidalv.infrastructure.presence.models;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PresenceResponse(
        @JsonProperty("id") String id,
        @JsonProperty("day") String day,
        @JsonProperty("week_year") String weekYear,
        @JsonProperty("week_month") String weekMonth,
        @JsonProperty("month") String month,
        @JsonProperty("year") String year,
        @JsonProperty("type") String type,
        @JsonProperty("service_id") String serviceID,
        @JsonProperty("teen_id") String teenID,
        @JsonProperty("is_active") boolean active,
        @JsonProperty("created_at") Instant createdAt,
        @JsonProperty("update_at") Instant updatedAt,
        @JsonProperty("deleted_at") Instant deletedAt
) {

}
