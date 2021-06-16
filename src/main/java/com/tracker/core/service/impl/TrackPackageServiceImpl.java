package com.tracker.core.service.impl;

import com.tracker.core.exception.NotFoundException;
import com.tracker.core.service.TrackPackageService;
import com.tracker.entrypoint.models.response.DeliveryStatusResponse;
import com.tracker.infrastructure.persistence.entity.DeliveryTrackerEntity;
import com.tracker.infrastructure.persistence.entity.PackageEntity;
import com.tracker.infrastructure.persistence.service.DeliveryTrackerPersistenceService;
import com.tracker.infrastructure.persistence.service.PackagePersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TrackPackageServiceImpl implements TrackPackageService {

    private final PackagePersistenceService packagePersistenceService;

    private final DeliveryTrackerPersistenceService deliveryTrackerPersistenceService;

    @Override
    public DeliveryStatusResponse getPackageStatus(String trackingId) {
        PackageEntity packageEntity = packagePersistenceService.findPackageByTrackingId(trackingId)
                .orElseThrow(() -> new NotFoundException("Package with tracking id " + trackingId + " was not found"));

        DeliveryStatusResponse response = new DeliveryStatusResponse();
        response.setDateUpdated(packageEntity.getDateModified().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        response.setCurrentStatus(packageEntity.getCurrentStatus().name());
        response.setTrackingId(trackingId);

        return response;
    }

    @Override
    public List<DeliveryStatusResponse> getPackageStatusHistory(String trackingId) {

        PackageEntity packageEntity = packagePersistenceService.findPackageByTrackingId(trackingId)
                .orElseThrow(() -> new NotFoundException("Package with tracking id " + trackingId + " was not found"));

        List<DeliveryTrackerEntity> trackers = deliveryTrackerPersistenceService.getAllByPackage(packageEntity);

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
