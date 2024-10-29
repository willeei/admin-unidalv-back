package com.willeei.unidalv.application.presence.update;

import com.willeei.unidalv.application.UseCase;

public abstract sealed class UpdatePresenceUseCase
        extends UseCase<UpdatePresenceCommand, UpdatePresenceOutput>
        permits DefaultUpdatePresenceUseCase {

}
