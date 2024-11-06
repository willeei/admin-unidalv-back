package br.com.wbrq.admin.unidalv.application.teen.retrieve.list;

import br.com.wbrq.admin.unidalv.application.UseCase;
import br.com.wbrq.admin.unidalv.domain.pagination.Pagination;
import br.com.wbrq.admin.unidalv.domain.pagination.SearchQuery;

public abstract sealed class ListTeenUseCase
        extends UseCase<SearchQuery, Pagination<TeenListOutput>>
        permits DefaultListTeenUseCase {

}
