package com.willeei.admin.unidalv.application.service.update;

import com.willeei.admin.unidalv.application.UseCase;

public abstract sealed class UpdateServiceUseCase
        extends UseCase<UpdateServiceCommand, UpdateServiceOutput>
        permits DefaultUpdateServiceUseCase {

}
