package com.tracker.infrastructure.persistence.service;

import com.tracker.infrastructure.persistence.entity.DeliveryTrackerEntity;
import com.tracker.infrastructure.persistence.entity.ItemEntity;

import java.util.List;

public interface DeliveryTrackerPersistenceService {

    List<DeliveryTrackerEntity> getAllByItem(ItemEntity itemEntity);

    DeliveryTrackerEntity save(DeliveryTrackerEntity deliveryTrackerEntity);
}
