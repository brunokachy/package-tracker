package com.tracker.core.service.impl;

import com.tracker.core.exception.BadRequestException;
import com.tracker.core.exception.NotFoundException;
import com.tracker.core.service.UpdateItemService;
import com.tracker.entrypoint.models.response.DeliveryStatusResponse;
import com.tracker.infrastructure.persistence.entity.DeliveryTrackerEntity;
import com.tracker.infrastructure.persistence.entity.ItemEntity;
import com.tracker.infrastructure.persistence.entity.enums.DeliveryStatus;
import com.tracker.infrastructure.persistence.service.DeliveryTrackerPersistenceService;
import com.tracker.infrastructure.persistence.service.ItemPersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class UpdateItemServiceImpl implements UpdateItemService {

    private final ItemPersistenceService itemPersistenceService;

    private final DeliveryTrackerPersistenceService deliveryTrackerPersistenceService;

    @Override
    @Transactional
    public DeliveryStatusResponse updateDeliveryStatus(String trackingId, String status) {

        ItemEntity itemEntity = itemPersistenceService.findItemByTrackingId(trackingId)
                .orElseThrow(() -> new NotFoundException("Package with tracking id " + trackingId + " was not found"));

        DeliveryStatus newStatus = DeliveryStatus.valueOf(status);

        if (!canUpdateStatus(itemEntity.getCurrentStatus(), newStatus)) {
            throw new BadRequestException("Package delivery cannot update from " + itemEntity.getCurrentStatus().name() + " to " + newStatus.name());
        }

        itemEntity.setCurrentStatus(newStatus);
        itemPersistenceService.saveItem(itemEntity);

        DeliveryTrackerEntity deliveryTrackerEntity = DeliveryTrackerEntity.builder()
                .item(itemEntity)
                .status(newStatus)
                .build();

        deliveryTrackerPersistenceService.save(deliveryTrackerEntity);

        DeliveryStatusResponse response = new DeliveryStatusResponse();
        response.setCurrentStatus(newStatus.name());
        response.setTrackingId(itemEntity.getTrackingId());
        response.setDateUpdated(itemEntity.getDateModified().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return response;
    }

    private boolean canUpdateStatus(DeliveryStatus currentStatus, DeliveryStatus newStatus) {

        switch (newStatus) {
            case PICKED_UP:
            case CANCELLED:
                return currentStatus.equals(DeliveryStatus.CREATED);
            case IN_TRANSIT:
                return currentStatus.equals(DeliveryStatus.PICKED_UP) || currentStatus.equals(DeliveryStatus.WAREHOUSE);
            case WAREHOUSE:
                return currentStatus.equals(DeliveryStatus.IN_TRANSIT);
            case DELIVERED:
                return currentStatus.equals(DeliveryStatus.IN_TRANSIT) || currentStatus.equals(DeliveryStatus.WAREHOUSE);
        }

        return false;
    }
}
