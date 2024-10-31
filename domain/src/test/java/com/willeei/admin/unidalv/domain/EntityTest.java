package com.willeei.admin.unidalv.domain;

import java.io.Serial;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.willeei.admin.unidalv.domain.event.DomainEvent;
import com.willeei.admin.unidalv.domain.utils.IdUtils;
import com.willeei.admin.unidalv.domain.utils.InstantUtils;
import com.willeei.admin.unidalv.domain.validation.ValidationHandler;

class EntityTest extends UnitTest {

    @Test
    void givenNullAsEvents_whenInstantiate_shouldBeOk() {
        // given
        final List<DomainEvent> events = null;

        // when
        final var anEntity = new DummyEntity(new DummyID(), events);

        // then
        Assertions.assertNotNull(anEntity.getDomainEvents());
        Assertions.assertTrue(anEntity.getDomainEvents().isEmpty());
    }

    @Test
    void givenDomainEvents_whenPassInConstructor_shouldCreateADefensiveClone() {
        // given
        final List<DomainEvent> events = new ArrayList<>();
        events.add((DomainEvent) InstantUtils::now);

        // when
        final var anEntity = new DummyEntity(new DummyID(), events);

        // then
        Assertions.assertNotNull(anEntity.getDomainEvents());
        Assertions.assertEquals(1, anEntity.getDomainEvents().size());

        Assertions.assertThrows(RuntimeException.class, () -> {
            final var actualEvents = anEntity.getDomainEvents();
            actualEvents.add((DomainEvent) InstantUtils::now);
        });
    }

    @Test
    void givenEmptyDomainEvents_whenCallsRegisterEvent_shouldAddEventToList() {
        // given
        final var expectedEvents = 1;
        final var anEntity = new DummyEntity(new DummyID(), new ArrayList<>());

        // when
        anEntity.registerEvent(new DummyEvent());

        // then
        Assertions.assertNotNull(anEntity.getDomainEvents());
        Assertions.assertEquals(expectedEvents, anEntity.getDomainEvents().size());
    }

    @Test
    void givenAFewDomainEvents_whenCallsPublishEvents_shouldCallPublisherAndClearTheList() {
        // given
        final var expectedEvents = 0;
        final var expectedSentEvents = 2;
        final var counter = new AtomicInteger(0);
        final var anEntity = new DummyEntity(new DummyID(), new ArrayList<>());

        anEntity.registerEvent(new DummyEvent());
        anEntity.registerEvent(new DummyEvent());

        Assertions.assertEquals(2, anEntity.getDomainEvents().size());

        // when
        anEntity.publishDomainEvents(event -> counter.incrementAndGet());

        // then
        Assertions.assertNotNull(anEntity.getDomainEvents());
        Assertions.assertEquals(expectedEvents, anEntity.getDomainEvents().size());
        Assertions.assertEquals(expectedSentEvents, counter.get());
    }

    public static class DummyEvent implements DomainEvent {

        @Serial
        private static final long serialVersionUID = 4500412262926825088L;

        @Override
        public Instant occurredOn() {
            return InstantUtils.now();
        }
    }

    public static class DummyID extends Identifier {

        private final String id;

        public DummyID() {
            this.id = IdUtils.uuid();
        }

        @Override
        public String getValue() {
            return this.id;
        }
    }

    public static class DummyEntity extends Entity<DummyID> {

        public DummyEntity(final DummyID anId, final List<DomainEvent> domainEvents) {
            super(anId, domainEvents);
        }

        @Override
        public void validate(final ValidationHandler handler) {
            // do nothing
        }
    }
}
