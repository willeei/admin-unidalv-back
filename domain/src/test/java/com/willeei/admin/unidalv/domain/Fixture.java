package com.willeei.admin.unidalv.domain;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;

import com.willeei.admin.unidalv.domain.presence.PresenceType;
import com.willeei.admin.unidalv.domain.service.Service;
import com.willeei.admin.unidalv.domain.service.ServiceID;
import com.willeei.admin.unidalv.domain.teen.Gender;
import com.willeei.admin.unidalv.domain.teen.Teen;
import com.willeei.admin.unidalv.domain.teen.TeenID;
import com.willeei.admin.unidalv.domain.utils.InstantUtils;

import net.datafaker.Faker;

public final class Fixture {

    private static final Faker FAKER = new Faker();

    public static String name() {
        return FAKER.name().fullName();
    }

    public static boolean bool() {
        return FAKER.bool().bool();
    }

    public static Instant now() {
        return InstantUtils.now();
    }

    public static Service service() {
        return Service.with(
                ServiceID.unique(),
                name(),
                FakePresence.FakeService.point(),
                bool(),
                new HashSet<>(),
                now(),
                now(),
                null
        );
    }

    public static Teen teen() {
        return Teen.with(
                TeenID.unique(),
                name(),
                FakeTeen.birthday(),
                bool(),
                bool(),
                bool(),
                FakeTeen.phone(),
                FakeTeen.phone(),
                name(),
                now(),
                null,
                FakeTeen.gender(),
                new HashSet<>(),
                now(),
                now(),
                null
        );
    }

    public static final class FakeTeen {

        public static String phone() {
            return FAKER.phoneNumber().cellPhone();
        }

        public static String birthday() {
            return FAKER.timeAndDate().birthday().toString();
        }

        public static Gender gender() {
            return FAKER.options().option(Gender.values());
        }
    }

    public static final class FakePresence {

        public static String day() {
            return FAKER.number().numberBetween(1, 31) + "";
        }

        public static String weekYear() {
            return FAKER.number().numberBetween(1, 52) + "";
        }

        public static String weekMonth() {
            return FAKER.number().numberBetween(1, 4) + "";
        }

        public static String month() {
            return FAKER.number().numberBetween(1, 12) + "";
        }

        public static String year() {
            return LocalDate.now().getYear() + "";
        }

        public static String justification() {
            return FAKER.lorem().sentence();
        }

        public static PresenceType type() {
            return FAKER.options().option(PresenceType.values());
        }

        public static final class FakeService {

            public static int point() {
                return FAKER.random().nextInt(50, 200);
            }

        }
    }
}
