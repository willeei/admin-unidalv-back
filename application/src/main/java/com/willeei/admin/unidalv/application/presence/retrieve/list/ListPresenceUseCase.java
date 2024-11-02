package com.willeei.admin.unidalv.application.presence.retrieve.list;

import com.willeei.admin.unidalv.application.UseCase;
import com.willeei.admin.unidalv.domain.pagination.Pagination;
import com.willeei.admin.unidalv.domain.pagination.SearchQuery;

public abstract sealed class ListPresenceUseCase
        extends UseCase<SearchQuery, Pagination<PresenceListOutput>>
        permits DefaultListPresenceUseCase {

}
