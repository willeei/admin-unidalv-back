package com.willeei.admin.unidalv.domain.event;

import java.io.Serializable;
import java.time.Instant;

public interface DomainEvent extends Serializable {

    Instant occurredOn();
}
