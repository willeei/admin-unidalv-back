package com.willeei.admin.unidalv.application.teen.retrieve.list;

import com.willeei.admin.unidalv.application.UseCase;
import com.willeei.admin.unidalv.domain.pagination.Pagination;
import com.willeei.admin.unidalv.domain.pagination.SearchQuery;

public abstract sealed class ListTeenUseCase
        extends UseCase<SearchQuery, Pagination<TeenListOutput>>
        permits DefaultListTeenUseCase {

}
