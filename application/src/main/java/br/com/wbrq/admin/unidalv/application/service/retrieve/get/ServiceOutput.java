package br.com.wbrq.admin.unidalv.application.service.retrieve.get;

import java.time.Instant;

import br.com.wbrq.admin.unidalv.domain.service.Service;

public record ServiceOutput(
        String id,
        String name,
        int point,
        boolean isActive,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt) {

    public static ServiceOutput from(Service service) {
        return new ServiceOutput(
                service.getId().getValue(),
                service.getName(),
                service.getPoint(),
                service.isActive(),
                service.getCreatedAt(),
                service.getUpdatedAt(),
                service.getDeletedAt()
        );
    }
}
