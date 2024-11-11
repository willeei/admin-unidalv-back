package br.com.wbrq.admin.unidalv.application.teen.create;

import br.com.wbrq.admin.unidalv.domain.teen.Teen;

public record CreateTeenOutput(String id) {

    public static CreateTeenOutput from(final String anId) {
        return new CreateTeenOutput(anId);
    }

    public static CreateTeenOutput from(final Teen aTeen) {
        return new CreateTeenOutput(aTeen.getId().getValue());
    }
}
