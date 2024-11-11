package br.com.wbrq.admin.unidalv.application.service.update;

import br.com.wbrq.admin.unidalv.domain.service.Service;

public record UpdateServiceOutput(String id) {

    public static UpdateServiceOutput from(final Service aService) {
        return new UpdateServiceOutput(aService.getId().getValue());
    }
}
