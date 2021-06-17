package com.tracker.core.service;

import com.tracker.core.domain.PagedData;
import com.tracker.entrypoint.models.response.ItemResponse;

public interface FetchItemService {

    PagedData<ItemResponse> getPagedItems(String deliveryStatus, int page, int size);
}
