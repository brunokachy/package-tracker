package com.tracker.infrastructure.persistence.service.impl;

import com.tracker.infrastructure.persistence.entity.ItemEntity;
import com.tracker.infrastructure.persistence.entity.enums.DeliveryStatus;
import com.tracker.infrastructure.persistence.repository.ItemRepository;
import com.tracker.infrastructure.persistence.service.ItemPersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemPersistenceServiceImpl implements ItemPersistenceService {

    private final ItemRepository repository;

    @Override
    public ItemEntity saveItem(ItemEntity itemEntity) {
        return repository.save(itemEntity);
    }

    @Override
    public Optional<ItemEntity> findItemByTrackingId(String trackingId) {
        return repository.findByTrackingId(trackingId);
    }

    @Override
    public Page<ItemEntity> getItems(int page, int size, DeliveryStatus status) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.getItems(status, pageable);
    }
}
