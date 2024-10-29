package com.willeei.admin.unidalv.application.teen.update;

import com.willeei.admin.unidalv.application.UseCase;

public abstract sealed class UpdateTeenUseCase
        extends UseCase<UpdateTeenCommand, UpdateTeenOutput>
        permits DefaultUpdateTeenUseCase {

}
