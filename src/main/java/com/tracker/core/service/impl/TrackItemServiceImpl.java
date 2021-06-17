package com.tracker.core.service.impl;

import com.tracker.core.exception.NotFoundException;
import com.tracker.core.service.TrackItemService;
import com.tracker.entrypoint.models.response.DeliveryStatusResponse;
import com.tracker.infrastructure.persistence.entity.DeliveryTrackerEntity;
import com.tracker.infrastructure.persistence.entity.ItemEntity;
import com.tracker.infrastructure.persistence.service.DeliveryTrackerPersistenceService;
import com.tracker.infrastructure.persistence.service.ItemPersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TrackItemServiceImpl implements TrackItemService {

    private final ItemPersistenceService itemPersistenceService;

    private final DeliveryTrackerPersistenceService deliveryTrackerPersistenceService;

    @Override
    public DeliveryStatusResponse getItemDeliveryStatus(String trackingId) {
        ItemEntity itemEntity = itemPersistenceService.findItemByTrackingId(trackingId)
                .orElseThrow(() -> new NotFoundException("Package with tracking id " + trackingId + " was not found"));

        DeliveryStatusResponse response = new DeliveryStatusResponse();
        response.setDateUpdated(itemEntity.getDateModified().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        response.setCurrentStatus(itemEntity.getCurrentStatus().name());
        response.setTrackingId(trackingId);

        return response;
    }

    @Override
    public List<DeliveryStatusResponse> getItemStatusHistory(String trackingId) {

        ItemEntity itemEntity = itemPersistenceService.findItemByTrackingId(trackingId)
                .orElseThrow(() -> new NotFoundException("Package with tracking id " + trackingId + " was not found"));

        List<DeliveryTrackerEntity> trackers = deliveryTrackerPersistenceService.getAllByItem(itemEntity);

        List<DeliveryStatusResponse> responses = new ArrayList<>();

        trackers.forEach(tracker -> {
            DeliveryStatusResponse response = new DeliveryStatusResponse();
            response.setDateUpdated(tracker.getDateCreated().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            response.setCurrentStatus(tracker.getStatus().name());
            response.setTrackingId(trackingId);
            responses.add(response);
        });

        return responses;
    }
}
