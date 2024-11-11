package br.com.wbrq.admin.unidalv.application.service.retrieve.get;

import br.com.wbrq.admin.unidalv.application.UseCase;

public abstract sealed class GetServiceByIdUseCase
        extends UseCase<String, ServiceOutput>
        permits DefaultGetServiceByIdUseCase {

}
