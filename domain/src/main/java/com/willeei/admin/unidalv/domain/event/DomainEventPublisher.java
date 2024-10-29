package com.willeei.admin.unidalv.domain.event;

@FunctionalInterface
public interface DomainEventPublisher {

    void publishEvent(DomainEvent event);
}
