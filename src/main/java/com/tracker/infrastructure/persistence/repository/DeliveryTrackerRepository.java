package com.tracker.infrastructure.persistence.repository;

import com.tracker.infrastructure.persistence.entity.DeliveryTrackerEntity;
import com.tracker.infrastructure.persistence.entity.PackageEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeliveryTrackerRepository extends CrudRepository<DeliveryTrackerEntity, Long> {

    List<DeliveryTrackerEntity> getAllByPackage_(PackageEntity packageEntity);
}
