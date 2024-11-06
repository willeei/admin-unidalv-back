package br.com.wbrq.admin.unidalv.application.teen.update;

import br.com.wbrq.admin.unidalv.domain.teen.Teen;

public record UpdateTeenOutput(String id) {

    public static UpdateTeenOutput from(final String anId) {
        return new UpdateTeenOutput(anId);
    }

    public static UpdateTeenOutput from(final Teen aTeen) {
        return new UpdateTeenOutput(aTeen.getId().getValue());
    }
}
