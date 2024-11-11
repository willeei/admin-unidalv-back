package br.com.wbrq.admin.unidalv.application.service.create;

import br.com.wbrq.admin.unidalv.application.UseCase;

public abstract sealed class CreateServiceUseCase
        extends UseCase<CreateServiceCommand, CreateServiceOutput>
        permits DefaultCreateServiceUseCase {

}
