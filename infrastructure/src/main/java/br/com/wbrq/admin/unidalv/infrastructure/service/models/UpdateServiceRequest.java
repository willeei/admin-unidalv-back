package br.com.wbrq.admin.unidalv.infrastructure.service.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateServiceRequest(
        @JsonProperty("name") String name,
        @JsonProperty("point") int point,
        @JsonProperty("is_active") boolean active) {

}
