package com.works.customer.models;

import java.util.List;
public class ProductModel {
    private List<Content> content;
    private Pageable pageable;
    private long totalPages;
    private long totalElements;
    private boolean last;
    private long size;
    private long number;
    private Sort sort;
    private long numberOfElements;
    private boolean first;
    private boolean empty;
    public List<Content> getContent() { return content; }
    public void setContent(List<Content> value) { this.content = value; }
    public Pageable getPageable() { return pageable; }
    public void setPageable(Pageable value) { this.pageable = value; }
    public long getTotalPages() { return totalPages; }
    public void setTotalPages(long value) { this.totalPages = value; }
    public long getTotalElements() { return totalElements; }
    public void setTotalElements(long value) { this.totalElements = value; }
    public boolean getLast() { return last; }
    public void setLast(boolean value) { this.last = value; }
    public long getSize() { return size; }
    public void setSize(long value) { this.size = value; }
    public long getNumber() { return number; }
    public void setNumber(long value) { this.number = value; }
    public Sort getSort() { return sort; }
    public void setSort(Sort value) { this.sort = value; }
    public long getNumberOfElements() { return numberOfElements; }
    public void setNumberOfElements(long value) { this.numberOfElements = value; }
    public boolean getFirst() { return first; }
    public void setFirst(boolean value) { this.first = value; }
    public boolean getEmpty() { return empty; }
    public void setEmpty(boolean value) { this.empty = value; }
}