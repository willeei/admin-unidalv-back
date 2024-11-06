package br.com.wbrq.admin.unidalv.application.presence.update;

import br.com.wbrq.admin.unidalv.application.UseCase;

public abstract sealed class UpdatePresenceUseCase
        extends UseCase<UpdatePresenceCommand, UpdatePresenceOutput>
        permits DefaultUpdatePresenceUseCase {

}
