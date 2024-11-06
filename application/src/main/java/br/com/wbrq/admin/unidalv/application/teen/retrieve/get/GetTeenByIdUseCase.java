package br.com.wbrq.admin.unidalv.application.teen.retrieve.get;

import br.com.wbrq.admin.unidalv.application.UseCase;

public abstract sealed class GetTeenByIdUseCase
        extends UseCase<String, TeenOutput>
        permits DefaultGetTeenByIdUseCase {

}
