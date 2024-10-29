package com.willeei.admin.unidalv.application.presence.create;

import com.willeei.admin.unidalv.application.UseCase;

public abstract sealed class CreatePresenceUseCase
        extends UseCase<CreatePresenceCommand, CreatePresenceOutput>
        permits DefaultCreatePresenceUseCase {

}
