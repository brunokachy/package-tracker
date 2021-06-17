package com.tracker.infrastructure.persistence.service.impl;

import com.tracker.infrastructure.persistence.entity.DeliveryTrackerEntity;
import com.tracker.infrastructure.persistence.entity.ItemEntity;
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
    public List<DeliveryTrackerEntity> getAllByItem(ItemEntity itemEntity) {
        return repository.getAllByItem(itemEntity);
    }

    @Override
    public DeliveryTrackerEntity save(DeliveryTrackerEntity deliveryTrackerEntity) {
        return repository.save(deliveryTrackerEntity);
    }
}
