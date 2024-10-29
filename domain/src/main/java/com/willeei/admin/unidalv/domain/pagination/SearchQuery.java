package com.willeei.admin.unidalv.domain.pagination;

public record SearchQuery(
        int page,
        int perPage,
        String terms,
        String sort,
        String direction
) {

}
