package br.com.wbrq.admin.unidalv.application.service.retrieve.list;

import br.com.wbrq.admin.unidalv.application.UseCase;
import br.com.wbrq.admin.unidalv.domain.pagination.Pagination;
import br.com.wbrq.admin.unidalv.domain.pagination.SearchQuery;

public abstract sealed class ListServiceUseCase extends UseCase<SearchQuery, Pagination<ServiceListOutput>>
        permits DefaultListServiceUseCase {

}
