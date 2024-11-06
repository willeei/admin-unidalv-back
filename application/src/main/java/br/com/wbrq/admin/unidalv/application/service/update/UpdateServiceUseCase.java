package br.com.wbrq.admin.unidalv.application.service.update;

import br.com.wbrq.admin.unidalv.application.UseCase;

public abstract sealed class UpdateServiceUseCase
        extends UseCase<UpdateServiceCommand, UpdateServiceOutput>
        permits DefaultUpdateServiceUseCase {

}
