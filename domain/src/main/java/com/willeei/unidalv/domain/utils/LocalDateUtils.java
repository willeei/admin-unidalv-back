package com.willeei.unidalv.domain.utils;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

public final class LocalDateUtils {

    private final String day;
    private final String weekYear;
    private final String weekMonth;
    private final String month;
    private final String year;

    private LocalDateUtils(
            final String aDay,
            final String aWeekYear,
            final String aWeekMonth,
            final String aMonth,
            final String aYear
    ) {
        this.day = aDay;
        this.weekYear = aWeekYear;
        this.weekMonth = aWeekMonth;
        this.month = aMonth;
        this.year = aYear;
    }

    public static LocalDateUtils with(final LocalDate aDate) {
        final var day = String.valueOf(aDate.getDayOfMonth());
        final var month = String.valueOf(aDate.getMonthValue());
        final var year = String.valueOf(aDate.getYear());

        final var brazil = Locale.forLanguageTag("pt-BR");
        final var weekYear = String.valueOf(aDate.get(WeekFields.of(brazil).weekOfWeekBasedYear()));
        final var weekMonth = String.valueOf(aDate.get(WeekFields.of(brazil).weekOfMonth()));
        return new LocalDateUtils(day, weekYear, weekMonth, month, year);
    }

    public String day() {
        return day;
    }

    public String weekYear() {
        return weekYear;
    }

    public String weekMonth() {
        return weekMonth;
    }

    public String month() {
        return month;
    }

    public String year() {
        return year;
    }
}
