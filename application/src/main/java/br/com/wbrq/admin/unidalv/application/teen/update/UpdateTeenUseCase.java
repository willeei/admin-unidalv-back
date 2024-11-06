package br.com.wbrq.admin.unidalv.application.teen.update;

import br.com.wbrq.admin.unidalv.application.UseCase;

public abstract sealed class UpdateTeenUseCase
        extends UseCase<UpdateTeenCommand, UpdateTeenOutput>
        permits DefaultUpdateTeenUseCase {

}
