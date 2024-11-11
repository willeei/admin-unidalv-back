package br.com.wbrq.admin.unidalv.infrastructure.service.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateServiceRequest(
       @JsonProperty("name") String name,
       @JsonProperty("point") int point,
       @JsonProperty("is_active") Boolean active
) {
}
