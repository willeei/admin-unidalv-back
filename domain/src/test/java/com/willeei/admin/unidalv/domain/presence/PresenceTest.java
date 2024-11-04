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

        final var expectedType = PresenceType.PRESENT;

        final var expectedService = Fixture.service();
        final var expectedTeen = Fixture.teen();

        // when
        final var actualPresence = Presence.newPresence(
                localDate,
                expectedType,
                null,
                expectedService,
                expectedTeen
        );

        // then
        Assertions.assertNotNull(actualPresence);
        Assertions.assertNotNull(actualPresence.getId());
        Assertions.assertEquals(expectedDay, actualPresence.getDay());
        Assertions.assertEquals(expectedWeekYear, actualPresence.getWeekYear());
        Assertions.assertEquals(expectedWeekMonth, actualPresence.getWeekMonth());
        Assertions.assertEquals(expectedMonth, actualPresence.getMonth());
        Assertions.assertEquals(expectedYear, actualPresence.getYear());
        Assertions.assertEquals(expectedType, actualPresence.getType());
        Assertions.assertNull(actualPresence.getJustification());
        Assertions.assertEquals(expectedService, actualPresence.getService());
        Assertions.assertEquals(expectedTeen, actualPresence.getTeen());
        Assertions.assertNotNull(actualPresence.getCreatedAt());
        Assertions.assertNotNull(actualPresence.getUpdatedAt());
    }

    @Test
    void givenAValidJustification_whenCallNewPresenceWithPresentType_thenShouldReceiveANotification() {
        // given
        final var localDate = LocalDate.now();
        final var expectedType = PresenceType.PRESENT;
        final var expectedService = Fixture.service();
        final var expectedTeen = Fixture.teen();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'justification' should only be user with type 'JUSTIFIED'";

        // when
        final var actualException = Assertions.assertThrows(
                NotificationException.class,
                () -> Presence.newPresence(
                        localDate,
                        expectedType,
                        "Justification",
                        expectedService,
                        expectedTeen
                )
        );

        // then
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
    }

    @Test
    void givenAValidJustification_whenCallNewPresenceWithAbsentType_thenShouldReceiveANotification() {
        // given
        final var localDate = LocalDate.now();
        final var expectedType = PresenceType.ABSENT;
        final var expectedService = Fixture.service();
        final var expectedTeen = Fixture.teen();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'justification' should only be user with type 'JUSTIFIED'";

        // when
        final var actualException = Assertions.assertThrows(
                NotificationException.class,
                () -> Presence.newPresence(
                        localDate,
                        expectedType,
                        "Justification",
                        expectedService,
                        expectedTeen
                )
        );

        // then
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
    }

    @Test
    void givenAnInvalidNullType_whenCallNewPresence_thenShouldReceiveANotification() {
        // given
        final var localDate = LocalDate.now();
        final var expectedService = Fixture.service();
        final var expectedTeen = Fixture.teen();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'type' should not be null";

        // when
        final var actualException = Assertions.assertThrows(
                NotificationException.class,
                () -> Presence.newPresence(localDate, null, null, expectedService, expectedTeen)
        );

        // then
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
    }

    @Test
    void givenAnInvalidNullDate_whenCallNewPresenceAndValidate_thenShouldReceiveError() {
        // given
        final var expectedService = Fixture.service();
        final var expectedTeen = Fixture.teen();
        final var expectedType = PresenceType.ABSENT;

        final var expectedException = new NullPointerException("date should not be null");
        final var actualException = Assertions.assertThrows(
                expectedException.getClass(),
                () -> Presence.newPresence(
                        null, expectedType, null, expectedService, expectedTeen
                )
        );

        Assertions.assertEquals("'date' should not be null", actualException.getMessage());
    }

    @Test
    void givenAValidJustifiedTypeAndAnInvalidNullJustification_whenCallNewPresence_thenShouldReceiveANotification() {
        // given
        final var localDate = LocalDate.now();
        final var expectedType = PresenceType.JUSTIFIED;
        final var expectedService = Fixture.service();
        final var expectedTeen = Fixture.teen();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'justification' should not be null";

        // when
        final var actualException = Assertions.assertThrows(
                NotificationException.class,
                () -> Presence.newPresence(
                        localDate,
                        expectedType,
                        null,
                        expectedService,
                        expectedTeen
                )
        );

        // then
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
    }

    @Test
    void givenAValidJustifiedTypeAndAnInvalidBlankJustification_whenCallNewPresence_thenShouldReceiveANotification() {
        // given
        final var localDate = LocalDate.now();
        final var expectedType = PresenceType.JUSTIFIED;
        final var expectedService = Fixture.service();
        final var expectedTeen = Fixture.teen();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'justification' should not be empty";

        // when
        final var actualException = Assertions.assertThrows(
                NotificationException.class,
                () -> Presence.newPresence(
                        localDate,
                        expectedType,
                        "",
                        expectedService,
                        expectedTeen
                )
        );

        // then
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
    }
}
