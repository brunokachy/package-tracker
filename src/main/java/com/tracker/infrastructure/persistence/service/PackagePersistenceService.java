package com.tracker.infrastructure.persistence.service;

import com.tracker.infrastructure.persistence.entity.PackageEntity;
import com.tracker.infrastructure.persistence.entity.enums.DeliveryStatus;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface PackagePersistenceService {

    PackageEntity savePackage(PackageEntity packageEntity);

    Optional<PackageEntity> findPackageByTrackingId(String trackingId);

    Page<PackageEntity> getPackages(int page, int size, DeliveryStatus status);
}
