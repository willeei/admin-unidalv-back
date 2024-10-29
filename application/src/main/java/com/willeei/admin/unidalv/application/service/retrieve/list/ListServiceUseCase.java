package com.willeei.admin.unidalv.application.service.retrieve.list;

import com.willeei.admin.unidalv.application.UseCase;
import com.willeei.admin.unidalv.domain.pagination.Pagination;
import com.willeei.admin.unidalv.domain.pagination.SearchQuery;

public abstract sealed class ListServiceUseCase extends UseCase<SearchQuery, Pagination<ServiceListOutput>>
        permits DefaultListServiceUseCase {

}
