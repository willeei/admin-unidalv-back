package com.willeei.admin.unidalv.application.teen.create;

import com.willeei.admin.unidalv.application.UseCase;

public abstract sealed class CreateTeenUseCase
        extends UseCase<CreateTeenCommand, CreateTeenOutput>
        permits DefaultCreateTeenUseCase {

}
