package com.willeei.admin.unidalv.application.presence.retrieve.get;

import com.willeei.admin.unidalv.application.UseCase;

public abstract sealed class GetPresenceByIdUseCase
        extends UseCase<String, PresenceOutput>
        permits DefaultGetPresenceByIdUseCase {

}
