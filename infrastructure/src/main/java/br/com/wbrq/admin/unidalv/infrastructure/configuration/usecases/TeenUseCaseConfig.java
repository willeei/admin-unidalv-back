package br.com.wbrq.admin.unidalv.infrastructure.configuration.usecases;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.wbrq.admin.unidalv.application.teen.create.CreateTeenUseCase;
import br.com.wbrq.admin.unidalv.application.teen.create.DefaultCreateTeenUseCase;
import br.com.wbrq.admin.unidalv.application.teen.delete.DefaultDeleteTeenUseCase;
import br.com.wbrq.admin.unidalv.application.teen.delete.DeleteTeenUseCase;
import br.com.wbrq.admin.unidalv.application.teen.retrieve.get.DefaultGetTeenByIdUseCase;
import br.com.wbrq.admin.unidalv.application.teen.retrieve.get.GetTeenByIdUseCase;
import br.com.wbrq.admin.unidalv.application.teen.retrieve.list.DefaultListTeenUseCase;
import br.com.wbrq.admin.unidalv.application.teen.retrieve.list.ListTeenUseCase;
import br.com.wbrq.admin.unidalv.application.teen.update.DefaultUpdateTeenUseCase;
import br.com.wbrq.admin.unidalv.application.teen.update.UpdateTeenUseCase;
import br.com.wbrq.admin.unidalv.domain.presence.PresenceGateway;
import br.com.wbrq.admin.unidalv.domain.teen.TeenGateway;

@Configuration
public class TeenUseCaseConfig {

    private final TeenGateway teenGateway;
    private final PresenceGateway presenceGateway;

    public TeenUseCaseConfig(final TeenGateway teenGateway, final PresenceGateway presenceGateway) {
        this.teenGateway = teenGateway;
        this.presenceGateway = presenceGateway;
    }

    @Bean
    public CreateTeenUseCase createTeenUseCase() {
        return new DefaultCreateTeenUseCase(teenGateway, presenceGateway);
    }

    @Bean
    public DeleteTeenUseCase deleteTeenUseCase() {
        return new DefaultDeleteTeenUseCase(teenGateway);
    }

    @Bean
    public GetTeenByIdUseCase getTeenByIdUseCase() {
        return new DefaultGetTeenByIdUseCase(teenGateway);
    }

    @Bean
    public ListTeenUseCase listTeenUseCase() {
        return new DefaultListTeenUseCase(teenGateway);
    }

    @Bean
    public UpdateTeenUseCase updateTeenUseCase() {
        return new DefaultUpdateTeenUseCase(teenGateway, presenceGateway);
    }
}
