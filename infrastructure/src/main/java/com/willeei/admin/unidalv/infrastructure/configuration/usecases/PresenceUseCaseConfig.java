package com.willeei.admin.unidalv.infrastructure.configuration.usecases;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.willeei.admin.unidalv.application.presence.create.CreatePresenceUseCase;
import com.willeei.admin.unidalv.application.presence.create.DefaultCreatePresenceUseCase;
import com.willeei.admin.unidalv.application.presence.delete.DefaultDeletePresenceUseCase;
import com.willeei.admin.unidalv.application.presence.delete.DeletePresenceUseCase;
import com.willeei.admin.unidalv.application.presence.retrieve.get.DefaultGetPresenceByIdUseCase;
import com.willeei.admin.unidalv.application.presence.retrieve.get.GetPresenceByIdUseCase;
import com.willeei.admin.unidalv.application.presence.retrieve.list.DefaultListPresenceUseCase;
import com.willeei.admin.unidalv.application.presence.retrieve.list.ListPresenceUseCase;
import com.willeei.admin.unidalv.application.presence.update.DefaultUpdatePresenceUseCase;
import com.willeei.admin.unidalv.application.presence.update.UpdatePresenceUseCase;
import com.willeei.admin.unidalv.domain.presence.PresenceGateway;
import com.willeei.admin.unidalv.domain.service.ServiceGateway;
import com.willeei.admin.unidalv.domain.teen.TeenGateway;

@Configuration
public class PresenceUseCaseConfig {

    private final PresenceGateway presenceGateway;
    private final ServiceGateway serviceGateway;
    private final TeenGateway teenGateway;

    public PresenceUseCaseConfig(
            final PresenceGateway presenceGateway,
            final ServiceGateway serviceGateway,
            final TeenGateway teenGateway
    ) {
        this.presenceGateway = presenceGateway;
        this.serviceGateway = serviceGateway;
        this.teenGateway = teenGateway;
    }

    @Bean
    public CreatePresenceUseCase createPresenceUseCase() {
        return new DefaultCreatePresenceUseCase(presenceGateway, serviceGateway, teenGateway);
    }

    @Bean
    public DeletePresenceUseCase deletePresenceUseCase() {
        return new DefaultDeletePresenceUseCase(presenceGateway);
    }

    @Bean
    public GetPresenceByIdUseCase getPresenceByIdUseCase() {
        return new DefaultGetPresenceByIdUseCase(presenceGateway);
    }

    @Bean
    public ListPresenceUseCase listPresenceUseCase() {
        return new DefaultListPresenceUseCase(presenceGateway);
    }

    @Bean
    public UpdatePresenceUseCase updatePresenceUseCase() {
        return new DefaultUpdatePresenceUseCase(presenceGateway);
    }
}
