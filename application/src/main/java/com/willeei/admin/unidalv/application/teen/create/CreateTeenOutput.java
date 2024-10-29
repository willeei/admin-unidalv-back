package com.willeei.admin.unidalv.application.teen.create;

import com.willeei.admin.unidalv.domain.teen.Teen;

public record CreateTeenOutput(String id) {

    public static CreateTeenOutput from(final String anId) {
        return new CreateTeenOutput(anId);
    }

    public static CreateTeenOutput from(final Teen aTeen) {
        return new CreateTeenOutput(aTeen.getId().getValue());
    }
}
