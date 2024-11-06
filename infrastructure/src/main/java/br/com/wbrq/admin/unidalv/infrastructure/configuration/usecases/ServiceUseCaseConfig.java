package br.com.wbrq.admin.unidalv.infrastructure.configuration.usecases;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.wbrq.admin.unidalv.application.service.create.CreateServiceUseCase;
import br.com.wbrq.admin.unidalv.application.service.create.DefaultCreateServiceUseCase;
import br.com.wbrq.admin.unidalv.application.service.delete.DefaultDeleteServiceUseCase;
import br.com.wbrq.admin.unidalv.application.service.delete.DeleteServiceUseCase;
import br.com.wbrq.admin.unidalv.application.service.retrieve.get.DefaultGetServiceByIdUseCase;
import br.com.wbrq.admin.unidalv.application.service.retrieve.get.GetServiceByIdUseCase;
import br.com.wbrq.admin.unidalv.application.service.retrieve.list.DefaultListServiceUseCase;
import br.com.wbrq.admin.unidalv.application.service.retrieve.list.ListServiceUseCase;
import br.com.wbrq.admin.unidalv.application.service.update.DefaultUpdateServiceUseCase;
import br.com.wbrq.admin.unidalv.application.service.update.UpdateServiceUseCase;
import br.com.wbrq.admin.unidalv.domain.presence.PresenceGateway;
import br.com.wbrq.admin.unidalv.domain.service.ServiceGateway;

@Configuration
public class ServiceUseCaseConfig {

    private final ServiceGateway serviceGateway;
    private final PresenceGateway presenceGateway;

    public ServiceUseCaseConfig(final ServiceGateway serviceGateway, final PresenceGateway presenceGateway) {
        this.serviceGateway = serviceGateway;
        this.presenceGateway = presenceGateway;
    }

    @Bean
    public CreateServiceUseCase createServiceUseCase() {
        return new DefaultCreateServiceUseCase(serviceGateway, presenceGateway);
    }

    @Bean
    public DeleteServiceUseCase deleteServiceUseCase() {
        return new DefaultDeleteServiceUseCase(serviceGateway);
    }

    @Bean
    public GetServiceByIdUseCase getServiceByIdUseCase() {
        return new DefaultGetServiceByIdUseCase(serviceGateway);
    }

    @Bean
    public ListServiceUseCase listServiceUseCase() {
        return new DefaultListServiceUseCase(serviceGateway);
    }

    @Bean
    public UpdateServiceUseCase updateServiceUseCase() {
        return new DefaultUpdateServiceUseCase(serviceGateway, presenceGateway);
    }
}
