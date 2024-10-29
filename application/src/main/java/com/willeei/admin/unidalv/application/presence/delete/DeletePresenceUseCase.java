package com.willeei.admin.unidalv.application.presence.delete;

import com.willeei.admin.unidalv.application.UnitUseCase;

public abstract sealed class DeletePresenceUseCase
extends UnitUseCase<String>
permits DefaultDeletePresenceUseCase {

}
