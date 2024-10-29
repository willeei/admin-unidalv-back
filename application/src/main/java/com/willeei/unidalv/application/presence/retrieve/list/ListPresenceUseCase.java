package com.willeei.unidalv.application.presence.retrieve.list;

import com.willeei.unidalv.application.UseCase;
import com.willeei.unidalv.domain.pagination.Pagination;
import com.willeei.unidalv.domain.pagination.SearchQuery;

public abstract sealed class ListPresenceUseCase
        extends UseCase<SearchQuery, Pagination<PresenceListOutput>>
        permits DefaultListPresenceByIdUseCase {

}
