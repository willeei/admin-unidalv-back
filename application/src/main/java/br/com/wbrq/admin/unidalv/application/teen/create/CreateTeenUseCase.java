package br.com.wbrq.admin.unidalv.application.teen.create;

import br.com.wbrq.admin.unidalv.application.UseCase;

public abstract sealed class CreateTeenUseCase
        extends UseCase<CreateTeenCommand, CreateTeenOutput>
        permits DefaultCreateTeenUseCase {

}
