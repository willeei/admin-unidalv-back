package com.willeei.unidalv.application.presence.retrieve.get;

import com.willeei.unidalv.application.UseCase;

public abstract sealed class GetPresenceByIdUseCase
        extends UseCase<String, PresenceOutput>
        permits DefaultGetPresenceByIdUseCase {

}
