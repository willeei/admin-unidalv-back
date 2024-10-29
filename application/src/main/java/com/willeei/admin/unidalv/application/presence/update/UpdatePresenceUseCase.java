package com.willeei.admin.unidalv.application.presence.update;

import com.willeei.admin.unidalv.application.UseCase;

public abstract sealed class UpdatePresenceUseCase
        extends UseCase<UpdatePresenceCommand, UpdatePresenceOutput>
        permits DefaultUpdatePresenceUseCase {

}
