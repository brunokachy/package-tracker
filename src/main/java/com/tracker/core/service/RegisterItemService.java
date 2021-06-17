package com.tracker.core.service;

import com.tracker.core.domain.Item;
import com.tracker.entrypoint.models.response.ItemResponse;
import com.tracker.infrastructure.persistence.entity.ItemEntity;

public interface RegisterItemService {

    ItemResponse registerItem(Item request);

    ItemResponse toResponse(ItemEntity itemEntity);
}
