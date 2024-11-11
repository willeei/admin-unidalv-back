package br.com.wbrq.admin.unidalv.application.service.create;

import br.com.wbrq.admin.unidalv.domain.service.Service;

public record CreateServiceOutput(String id) {

    public static CreateServiceOutput from(final Service aService) {
        return new CreateServiceOutput(aService.getId().getValue());
    }
}
