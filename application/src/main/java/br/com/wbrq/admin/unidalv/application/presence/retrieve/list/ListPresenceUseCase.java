package br.com.wbrq.admin.unidalv.application.presence.retrieve.list;

import br.com.wbrq.admin.unidalv.application.UseCase;
import br.com.wbrq.admin.unidalv.domain.pagination.Pagination;
import br.com.wbrq.admin.unidalv.domain.pagination.SearchQuery;

public abstract sealed class ListPresenceUseCase
        extends UseCase<SearchQuery, Pagination<PresenceListOutput>>
        permits DefaultListPresenceUseCase {

}
