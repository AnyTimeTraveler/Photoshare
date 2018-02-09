package org.simonscode.photoshare.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class OffsetLimitPagable implements Pageable {

    private final int page;
    private final int offset;
    private final int limit;
    private final Sort sort;

    private OffsetLimitPagable(int page, int offset, int limit, Sort sort) {
        this.page = page;
        this.offset = offset;
        this.limit = limit;
        this.sort = sort;
    }

    public OffsetLimitPagable(int offset, int limit, Sort sort) {
        this(1, offset, limit, sort);
    }

    @Override
    public int getPageNumber() {
        return page;
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public int getOffset() {
        return offset * page;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return new OffsetLimitPagable(page + 1, offset, limit, sort);
    }

    @Override
    public Pageable previousOrFirst() {
        return new OffsetLimitPagable(hasPrevious() ? page - 1 : 1, offset, limit, sort);
    }

    @Override
    public Pageable first() {
        return new OffsetLimitPagable(1, offset, limit, sort);
    }

    @Override
    public boolean hasPrevious() {
        return page > 1;
    }
}
