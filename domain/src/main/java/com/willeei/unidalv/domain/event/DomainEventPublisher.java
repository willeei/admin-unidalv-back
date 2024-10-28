package com.willeei.unidalv.domain.event;

@FunctionalInterface
public interface DomainEventPublisher {

    void publishEvent(DomainEvent event);
}
