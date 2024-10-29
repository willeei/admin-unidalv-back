package com.willeei.unidalv.application.presence.delete;

import com.willeei.unidalv.application.UnitUseCase;

public abstract sealed class DeletePresenceUseCase
extends UnitUseCase<String>
permits DefaultDeletePresenceUseCase {

}
