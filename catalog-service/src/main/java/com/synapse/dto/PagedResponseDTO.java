package com.synapse.dto;

import java.util.List;

public class PagedResponseDTO<T> {
    public List<T> data;
    public long totalElements;
    public int totalPages;
    public int currentPage;
}
