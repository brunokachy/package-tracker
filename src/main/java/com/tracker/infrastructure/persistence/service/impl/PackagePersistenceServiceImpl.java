package com.tracker.infrastructure.persistence.service.impl;

import com.tracker.infrastructure.persistence.entity.PackageEntity;
import com.tracker.infrastructure.persistence.entity.enums.DeliveryStatus;
import com.tracker.infrastructure.persistence.repository.PackageRepository;
import com.tracker.infrastructure.persistence.service.PackagePersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PackagePersistenceServiceImpl implements PackagePersistenceService {

    private final PackageRepository repository;

    @Override
    public PackageEntity savePackage(PackageEntity packageEntity) {
        return repository.save(packageEntity);
    }

    @Override
    public Optional<PackageEntity> findPackageByTrackingId(String trackingId) {
        return repository.findByTrackingId(trackingId);
    }

    @Override
    public Page<PackageEntity> getPackages(int page, int size, DeliveryStatus status) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.getPackages(status, pageable);
    }
}
