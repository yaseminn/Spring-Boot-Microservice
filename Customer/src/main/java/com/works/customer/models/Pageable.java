package com.works.customer.models;

public class Pageable {
    private long pageNumber;
    private long pageSize;
    private Sort sort;
    private long offset;
    private boolean paged;
    private boolean unpaged;
    public long getPageNumber() { return pageNumber; }
    public void setPageNumber(long value) { this.pageNumber = value; }
    public long getPageSize() { return pageSize; }
    public void setPageSize(long value) { this.pageSize = value; }
    public Sort getSort() { return sort; }
    public void setSort(Sort value) { this.sort = value; }
    public long getOffset() { return offset; }
    public void setOffset(long value) { this.offset = value; }
    public boolean getPaged() { return paged; }
    public void setPaged(boolean value) { this.paged = value; }
    public boolean getUnpaged() { return unpaged; }
    public void setUnpaged(boolean value) { this.unpaged = value; }
}
