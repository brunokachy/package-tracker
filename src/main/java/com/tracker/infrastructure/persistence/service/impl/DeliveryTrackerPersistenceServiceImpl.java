package com.tracker.infrastructure.persistence.service.impl;

import com.tracker.infrastructure.persistence.entity.DeliveryTrackerEntity;
import com.tracker.infrastructure.persistence.entity.PackageEntity;
import com.tracker.infrastructure.persistence.repository.DeliveryTrackerRepository;
import com.tracker.infrastructure.persistence.service.DeliveryTrackerPersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryTrackerPersistenceServiceImpl implements DeliveryTrackerPersistenceService {

    private final DeliveryTrackerRepository repository;

    @Override
    public List<DeliveryTrackerEntity> getAllByPackage(PackageEntity packageEntity) {
        return repository.getAllByPackage_(packageEntity);
    }

    @Override
    public DeliveryTrackerEntity save(DeliveryTrackerEntity deliveryTrackerEntity) {
        return repository.save(deliveryTrackerEntity);
    }
}
