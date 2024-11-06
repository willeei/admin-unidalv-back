package br.com.wbrq.admin.unidalv.application.presence.delete;

import br.com.wbrq.admin.unidalv.application.UnitUseCase;

public abstract sealed class DeletePresenceUseCase
        extends UnitUseCase<String>
        permits DefaultDeletePresenceUseCase {

}
