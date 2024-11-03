package com.willeei.admin.unidalv.infrastructure.presence.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.willeei.admin.unidalv.domain.presence.PresenceType;

public record UpdatePresenceRequest(
        @JsonProperty("date") LocalDate date,
        @JsonProperty("type") PresenceType type,
        @JsonProperty("justification") String justification,
        @JsonProperty("is_active") Boolean active
) {

}
