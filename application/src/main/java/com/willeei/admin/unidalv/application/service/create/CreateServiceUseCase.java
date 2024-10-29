package com.willeei.admin.unidalv.application.service.create;

import com.willeei.admin.unidalv.application.UseCase;

public abstract sealed class CreateServiceUseCase
        extends UseCase<CreateServiceCommand, CreateServiceOutput>
        permits DefaultCreateServiceUseCase {

}
