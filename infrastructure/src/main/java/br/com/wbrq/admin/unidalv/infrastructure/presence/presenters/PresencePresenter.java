package br.com.wbrq.admin.unidalv.infrastructure.presence.presenters;

import br.com.wbrq.admin.unidalv.application.presence.retrieve.get.PresenceOutput;
import br.com.wbrq.admin.unidalv.application.presence.retrieve.list.PresenceListOutput;
import br.com.wbrq.admin.unidalv.domain.utils.LocalDateUtils;
import br.com.wbrq.admin.unidalv.infrastructure.presence.models.PresenceListResponse;
import br.com.wbrq.admin.unidalv.infrastructure.presence.models.PresenceResponse;

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
                output.justification(),
                output.service().getId().getValue(),
                output.teen().getId().getValue(),
                output.createdAt(),
                output.updatedAt()
        );
    }

    static PresenceListResponse present(final PresenceListOutput output) {
        return new PresenceListResponse(
                output.id(),
                LocalDateUtils.toLocalDate(output.day(), output.month(), output.year()),
                output.type().name(),
                output.teen().getId().getValue(),
                output.createdAt().toString(),
                output.updatedAt().toString()
        );
    }
}
