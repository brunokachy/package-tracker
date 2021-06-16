package com.tracker.infrastructure.persistence.service;

import com.tracker.infrastructure.persistence.entity.DeliveryTrackerEntity;
import com.tracker.infrastructure.persistence.entity.PackageEntity;

import java.util.List;

public interface DeliveryTrackerPersistenceService {

    List<DeliveryTrackerEntity> getAllByPackage(PackageEntity packageEntity);

    DeliveryTrackerEntity save(DeliveryTrackerEntity deliveryTrackerEntity);
}
