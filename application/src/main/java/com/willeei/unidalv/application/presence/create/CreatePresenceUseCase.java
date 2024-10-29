package com.willeei.unidalv.application.presence.create;

import com.willeei.unidalv.application.UseCase;

public abstract sealed class CreatePresenceUseCase
        extends UseCase<CreatePresenceCommand, CreatePresenceOutput>
        permits DefaultCreatePresenceUseCase {

}
