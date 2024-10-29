package com.willeei.admin.unidalv.application.teen.update;

import com.willeei.admin.unidalv.domain.teen.Teen;

public record UpdateTeenOutput(String id) {

    public static UpdateTeenOutput from(final String anId) {
        return new UpdateTeenOutput(anId);
    }

    public static UpdateTeenOutput from(final Teen aTeen) {
        return new UpdateTeenOutput(aTeen.getId().getValue());
    }
}
