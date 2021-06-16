package com.tracker.infrastructure.persistence.repository;

import com.tracker.infrastructure.persistence.entity.PackageEntity;
import com.tracker.infrastructure.persistence.entity.enums.DeliveryStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PackageRepository extends CrudRepository<PackageEntity, Long> {

    Optional<PackageEntity> findByTrackingId(String trackingId);

    @Query(value = "select p from PackageEntity p WHERE p.currentStatus = :status")
    Page<PackageEntity> getPackages(@Param("status") DeliveryStatus status, Pageable pageable);
}
