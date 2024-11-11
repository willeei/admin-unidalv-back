package br.com.wbrq.admin.unidalv.infrastructure.configuration.usecases;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.wbrq.admin.unidalv.application.presence.create.CreatePresenceUseCase;
import br.com.wbrq.admin.unidalv.application.presence.create.DefaultCreatePresenceUseCase;
import br.com.wbrq.admin.unidalv.application.presence.delete.DefaultDeletePresenceUseCase;
import br.com.wbrq.admin.unidalv.application.presence.delete.DeletePresenceUseCase;
import br.com.wbrq.admin.unidalv.application.presence.retrieve.get.DefaultGetPresenceByIdUseCase;
import br.com.wbrq.admin.unidalv.application.presence.retrieve.get.GetPresenceByIdUseCase;
import br.com.wbrq.admin.unidalv.application.presence.retrieve.list.DefaultListPresenceUseCase;
import br.com.wbrq.admin.unidalv.application.presence.retrieve.list.ListPresenceUseCase;
import br.com.wbrq.admin.unidalv.application.presence.update.DefaultUpdatePresenceUseCase;
import br.com.wbrq.admin.unidalv.application.presence.update.UpdatePresenceUseCase;
import br.com.wbrq.admin.unidalv.domain.presence.PresenceGateway;
import br.com.wbrq.admin.unidalv.domain.service.ServiceGateway;
import br.com.wbrq.admin.unidalv.domain.teen.TeenGateway;

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
