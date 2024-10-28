package com.willeei.unidalv.domain.presence;

import com.willeei.unidalv.domain.Fixture;
import com.willeei.unidalv.domain.UnitTest;
import com.willeei.unidalv.domain.exceptions.NotificationException;
import com.willeei.unidalv.domain.utils.LocalDateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
        final var expectedWorship = Fixture.worship();
        final var expectedValid = true;

        // when
        final var actualPresence = Presence.newPresence(localDate, expectedType, expectedWorship, expectedValid);

        // then
        Assertions.assertNotNull(actualPresence);
        Assertions.assertNotNull(actualPresence.getId());
        Assertions.assertEquals(expectedDay, actualPresence.day());
        Assertions.assertEquals(expectedWeekYear, actualPresence.weekYear());
        Assertions.assertEquals(expectedWeekMonth, actualPresence.weekMonth());
        Assertions.assertEquals(expectedMonth, actualPresence.month());
        Assertions.assertEquals(expectedYear, actualPresence.year());
        Assertions.assertEquals(expectedType, actualPresence.type());
        Assertions.assertEquals(expectedWorship, actualPresence.worship());
        Assertions.assertEquals(expectedValid, actualPresence.isValid());
        Assertions.assertNotNull(actualPresence.createdAt());
        Assertions.assertNotNull(actualPresence.updatedAt());
        Assertions.assertNull(actualPresence.deletedAt());

    }

    @Test
    void givenAnInvalidNullType_whenCallNewPresence_thenShouldReceiveANotification() {
        // given
        final var localDate = LocalDate.now();
        final var expectedWorship = Fixture.worship();
        final var expectedValid = true;

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'type' should not be null";

        // when
        final var actualException = Assertions.assertThrows(
                NotificationException.class,
                () -> Presence.newPresence(localDate, null, expectedWorship, expectedValid)
        );

        // then
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
    }

    @Test
    void givenAnInvalidNullDate_whenCallNewPresenceAndValidade_thenShouldReceiveError() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> Presence.newPresence(null, Fixture.Presence.type(), Fixture.worship(), Fixture.bool())
        );
    }
}