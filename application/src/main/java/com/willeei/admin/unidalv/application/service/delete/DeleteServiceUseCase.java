package com.willeei.admin.unidalv.application.service.delete;

import com.willeei.admin.unidalv.application.UnitUseCase;

public abstract sealed class DeleteServiceUseCase
        extends UnitUseCase<String>
        permits DefaultDeleteServiceUseCase {

}
