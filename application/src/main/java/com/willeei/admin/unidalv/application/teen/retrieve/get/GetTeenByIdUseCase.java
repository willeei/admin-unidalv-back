package com.willeei.admin.unidalv.application.teen.retrieve.get;

import com.willeei.admin.unidalv.application.UseCase;

public abstract sealed class GetTeenByIdUseCase
        extends UseCase<String, TeenOutput>
        permits DefaultGetTeenByIdUseCase {

}
