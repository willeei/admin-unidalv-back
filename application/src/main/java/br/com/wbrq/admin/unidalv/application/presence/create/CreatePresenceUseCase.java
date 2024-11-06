package br.com.wbrq.admin.unidalv.application.presence.create;

import br.com.wbrq.admin.unidalv.application.UseCase;

public abstract sealed class CreatePresenceUseCase
        extends UseCase<CreatePresenceCommand, CreatePresenceOutput>
        permits DefaultCreatePresenceUseCase {

}
