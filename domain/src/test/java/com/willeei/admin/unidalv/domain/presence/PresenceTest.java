package com.willeei.admin.unidalv.domain.presence;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.willeei.admin.unidalv.domain.Fixture;
import com.willeei.admin.unidalv.domain.UnitTest;
import com.willeei.admin.unidalv.domain.exceptions.NotificationException;
import com.willeei.admin.unidalv.domain.utils.LocalDateUtils;

class PresenceTest extends UnitTest {

    @Test
    void givenAValidParams_whenCallNewPresence_thenInstantiateAPresence() {
        // given
        final var localDate = LocalDate.now();
        final var date = LocalDateUtils.with(localDate);
        final var expectedDay = date.day();
        final var expectedWeekYear = date.weekYear();
        final var expectedWeekMonth = date.weekMonth();
        final var expectedMonth = date.month();
        final var expectedYear = date.year();

        final var expectedType = Fixture.Presence.type();
        final var expectedValid = true;

        final var expectedService = Fixture.service();

        // when
        final var actualPresence
                = Presence.newPresence(localDate, expectedType, null, expectedService, null, expectedValid);

        // then
        Assertions.assertNotNull(actualPresence);
        Assertions.assertNotNull(actualPresence.getId());
        Assertions.assertEquals(expectedDay, actualPresence.getDay());
        Assertions.assertEquals(expectedWeekYear, actualPresence.getWeekYear());
        Assertions.assertEquals(expectedWeekMonth, actualPresence.getWeekMonth());
        Assertions.assertEquals(expectedMonth, actualPresence.getMonth());
        Assertions.assertEquals(expectedYear, actualPresence.getYear());
        Assertions.assertEquals(expectedType, actualPresence.getType());
        Assertions.assertEquals(expectedValid, actualPresence.isActive());
        Assertions.assertNotNull(actualPresence.getCreatedAt());
        Assertions.assertNotNull(actualPresence.getUpdatedAt());
        Assertions.assertNull(actualPresence.getDeletedAt());

    }

    @Test
    void givenAnInvalidNullType_whenCallNewPresence_thenShouldReceiveANotification() {
        // given
        final var localDate = LocalDate.now();
        final var expectedValid = true;
        final var expectedService = Fixture.service();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'type' should not be null";

        // when
        final var actualException = Assertions.assertThrows(
                NotificationException.class,
                () -> Presence.newPresence(localDate, null, null, expectedService, null, expectedValid)
        );

        // then
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
    }

    @Test
    void givenAnInvalidNullDate_whenCallNewPresenceAndValidade_thenShouldReceiveError() {
        // given
        final var expectedException = new NullPointerException("date should not be null");
        final var actualException = Assertions.assertThrows(
                expectedException.getClass(),
                () -> Presence.newPresence(
                        null, Fixture.Presence.type(), null, null, null, Fixture.bool()
                )
        );

        Assertions.assertEquals("'date' should not be null", actualException.getMessage());
    }
}
