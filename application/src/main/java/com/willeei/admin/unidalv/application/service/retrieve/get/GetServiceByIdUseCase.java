package com.willeei.admin.unidalv.application.service.retrieve.get;

import com.willeei.admin.unidalv.application.UseCase;

public abstract sealed class GetServiceByIdUseCase
        extends UseCase<String, ServiceOutput>
        permits DefaultGetServiceByIdUseCase {

}
