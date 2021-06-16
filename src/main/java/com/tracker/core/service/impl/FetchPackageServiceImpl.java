package com.tracker.core.service.impl;

import com.tracker.core.domain.PagedData;
import com.tracker.core.service.FetchPackageService;
import com.tracker.core.service.RegisterPackageService;
import com.tracker.entrypoint.models.response.PackageResponse;
import com.tracker.infrastructure.persistence.entity.PackageEntity;
import com.tracker.infrastructure.persistence.entity.enums.DeliveryStatus;
import com.tracker.infrastructure.persistence.service.PackagePersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FetchPackageServiceImpl implements FetchPackageService {

    private final PackagePersistenceService packagePersistenceService;
    private final RegisterPackageService registerPackageService;

    @Override
    public PagedData<PackageResponse> getPagedPackages(String deliveryStatus, int page, int size) {

        DeliveryStatus status = DeliveryStatus.valueOf(deliveryStatus);

        Page<PackageEntity> packagePage = packagePersistenceService.getPackages(page, size, status);

        PagedData<PackageResponse> response = new PagedData<>();
        response.setRecords(packagePage.stream().map(registerPackageService::toResponse).collect(Collectors.toList()));
        response.setTotalRecords(packagePage.getTotalElements());
        response.setTotalPages(packagePage.getTotalPages());

        return response;
    }
}
