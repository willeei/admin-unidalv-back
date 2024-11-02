package com.willeei.admin.unidalv.infrastructure.presence.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.willeei.admin.unidalv.domain.presence.PresenceType;

public record CreatePresenceRequest(
        @JsonProperty("date") LocalDate date,
        @JsonProperty("type") PresenceType type,
        @JsonProperty("is_active") Boolean active,
        @JsonProperty("service_id") String serviceId,
        @JsonProperty("teen_id") String teenId
) {

}
