package br.com.wbrq.admin.unidalv.domain.utils;

import java.time.LocalDate;

public final class BirthDateUtils {

    private BirthDateUtils() {
    }

    public static int age(final String birthDate) {
        return LocalDate.parse(birthDate).until(LocalDate.now()).getYears();
    }
}
