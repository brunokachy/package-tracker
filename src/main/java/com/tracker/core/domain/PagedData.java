package com.tracker.core.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class PagedData<T> {
    private long totalRecords;
    private long totalPages;
    private List<T> records;

    public PagedData(long totalRecords, long totalPages, List<T> records) {
        this.totalRecords = totalRecords;
        this.totalPages = totalPages;
        this.records = records;
    }
}
