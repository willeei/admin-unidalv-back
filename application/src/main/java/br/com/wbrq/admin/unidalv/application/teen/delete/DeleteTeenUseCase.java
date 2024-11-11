package br.com.wbrq.admin.unidalv.application.teen.delete;

import br.com.wbrq.admin.unidalv.application.UnitUseCase;

public abstract sealed class DeleteTeenUseCase
        extends UnitUseCase<String>
        permits DefaultDeleteTeenUseCase {

}
