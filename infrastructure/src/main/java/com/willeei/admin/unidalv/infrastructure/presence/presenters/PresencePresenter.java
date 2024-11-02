package com.willeei.admin.unidalv.infrastructure.presence.presenters;

import com.willeei.admin.unidalv.application.presence.retrieve.get.PresenceOutput;
import com.willeei.admin.unidalv.application.presence.retrieve.list.PresenceListOutput;
import com.willeei.admin.unidalv.domain.utils.LocalDateUtils;
import com.willeei.admin.unidalv.infrastructure.presence.models.PresenceListResponse;
import com.willeei.admin.unidalv.infrastructure.presence.models.PresenceResponse;

public interface PresencePresenter {

    static PresenceResponse present(final PresenceOutput output) {
        return new PresenceResponse(
                output.id(),
                output.day(),
                output.weekYear(),
                output.weekMonth(),
                output.month(),
                output.year(),
                output.type().name(),
                output.service().getId().getValue(),
                output.teen().getId().getValue(),
                output.isActive(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }

    static PresenceListResponse present(final PresenceListOutput output) {
        return new PresenceListResponse(
                output.id(),
                LocalDateUtils.toLocalDate(output.day(), output.month(), output.year()),
                output.type().name(),
                output.isActive(),
                output.teen().getId().getValue(),
                output.createdAt().toString(),
                output.updatedAt().toString()
        );
    }
}
