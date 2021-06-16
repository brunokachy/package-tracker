package com.tracker.core.service.impl;

import com.tracker.core.domain.PackageModel;
import com.tracker.core.service.RegisterPackageService;
import com.tracker.core.utils.CodeGenerator;
import com.tracker.entrypoint.models.response.PackageResponse;
import com.tracker.infrastructure.persistence.entity.PackageEntity;
import com.tracker.infrastructure.persistence.service.PackagePersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class RegisterPackageServiceImpl implements RegisterPackageService {

    private final PackagePersistenceService packagePersistenceService;

    @Override
    public PackageResponse registerPackage(PackageModel request) {

        PackageEntity packageEntity = PackageEntity.builder()
                .deliveryAddress(request.getDeliveryAddress())
                .description(request.getDescription())
                .trackingId(CodeGenerator.randomString(8))
                .pickUpAddress(request.getPickUpAddress())
                .build();

        packageEntity = packagePersistenceService.savePackage(packageEntity);

        return toResponse(packageEntity);
    }

    @Override
    public PackageResponse toResponse(PackageEntity packageEntity) {

        PackageResponse response = new PackageResponse();
        response.setDateCreated(packageEntity.getDateCreated().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        response.setCurrentStatus(packageEntity.getCurrentStatus().name());
        response.setDateUpdated(packageEntity.getDateModified().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        response.setDescription(packageEntity.getDescription());
        response.setDeliveryAddress(packageEntity.getDeliveryAddress());
        response.setTrackingId(packageEntity.getTrackingId());
        response.setPickUpAddress(packageEntity.getPickUpAddress());

        return response;
    }


}
