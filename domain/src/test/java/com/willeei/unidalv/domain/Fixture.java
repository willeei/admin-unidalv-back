package com.willeei.unidalv.domain;

import com.willeei.admin.unidalv.domain.presence.PresenceType;
import com.willeei.admin.unidalv.domain.service.Service;
import com.willeei.admin.unidalv.domain.service.ServiceID;

import net.datafaker.Faker;

public final class Fixture {

    private static final Faker FAKER = new Faker();

    public static String name() {
        return FAKER.name().fullName();
    }

    public static boolean bool() {
        return FAKER.bool().bool();
    }

    public static Service service() {
        return Service.with(
                ServiceID.unique(),
                name(),
                Presence.Service.point(),
                bool(),
                null,
                null,
                null,
                null
        );
    }

    public static final class Presence {

        public static PresenceType type() {
            return FAKER.options().option(PresenceType.values());
        }

        public static final class Service {

            public static int point() {
                return FAKER.random().nextInt(50, 200);
            }

        }
    }
}
