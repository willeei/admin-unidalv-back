package com.willeei.admin.unidalv.application.teen.delete;

import com.willeei.admin.unidalv.application.UnitUseCase;

public abstract sealed class DeleteTeenUseCase
        extends UnitUseCase<String>
        permits DefaultDeleteTeenUseCase {

}
