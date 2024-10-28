package com.willeei.unidalv.domain;

import com.willeei.unidalv.domain.presence.PresenceType;
import com.willeei.unidalv.domain.presence.Worship;
import com.willeei.unidalv.domain.utils.IdUtils;
import net.datafaker.Faker;

public final class Fixture {

    private static final Faker FAKER = new Faker();

    public static String name() {
        return FAKER.name().fullName();
    }

    public static boolean bool() {
        return FAKER.bool().bool();
    }

    public static Worship worship() {
        return Worship.with(IdUtils.uuid(), name(), Presence.Worship.point());
    }

    public static final class Presence {

        public static PresenceType type() {
            return FAKER.options().option(PresenceType.values());
        }

        public static final class Worship {

            public static int point() {
                return FAKER.random().nextInt(50, 200);
            }

        }
    }
}
