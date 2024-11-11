package br.com.wbrq.admin.unidalv.application.service.delete;

import br.com.wbrq.admin.unidalv.application.UnitUseCase;

public abstract sealed class DeleteServiceUseCase
        extends UnitUseCase<String>
        permits DefaultDeleteServiceUseCase {

}
