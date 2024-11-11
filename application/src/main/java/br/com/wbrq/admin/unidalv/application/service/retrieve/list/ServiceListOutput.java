package br.com.wbrq.admin.unidalv.application.service.retrieve.list;

import java.time.Instant;

import br.com.wbrq.admin.unidalv.domain.service.Service;

public record ServiceListOutput(
        String id,
        String name,
        int point,
        boolean isActive,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt) {

    public static ServiceListOutput from(final Service aService) {
        return new ServiceListOutput(
                aService.getId().getValue(),
                aService.getName(),
                aService.getPoint(),
                aService.isActive(),
                aService.getCreatedAt(),
                aService.getUpdatedAt(),
                aService.getDeletedAt()
        );
    }
}
