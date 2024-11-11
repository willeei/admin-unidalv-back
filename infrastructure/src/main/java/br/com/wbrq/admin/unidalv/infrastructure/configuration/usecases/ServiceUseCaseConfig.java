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
import br.com.wbrq.admin.unidalv.domain.service.ServiceGateway;

@Configuration
public class ServiceUseCaseConfig {

    private final ServiceGateway serviceGateway;

    public ServiceUseCaseConfig(final ServiceGateway serviceGateway) {
        this.serviceGateway = serviceGateway;
    }

    @Bean
    public CreateServiceUseCase createServiceUseCase() {
        return new DefaultCreateServiceUseCase(serviceGateway);
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
        return new DefaultUpdateServiceUseCase(serviceGateway);
    }
}
