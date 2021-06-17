package com.tracker.infrastructure.persistence.service;

import com.tracker.infrastructure.persistence.entity.ItemEntity;
import com.tracker.infrastructure.persistence.entity.enums.DeliveryStatus;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ItemPersistenceService {

    ItemEntity saveItem(ItemEntity itemEntity);

    Optional<ItemEntity> findItemByTrackingId(String trackingId);

    Page<ItemEntity> getItems(int page, int size, DeliveryStatus status);
}
