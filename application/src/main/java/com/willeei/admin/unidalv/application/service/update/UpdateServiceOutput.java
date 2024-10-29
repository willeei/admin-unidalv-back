package com.willeei.admin.unidalv.application.service.update;

import com.willeei.admin.unidalv.domain.service.Service;

public record UpdateServiceOutput(String id) {

    public static UpdateServiceOutput from(final Service aService) {
        return new UpdateServiceOutput(aService.getId().getValue());
    }
}
