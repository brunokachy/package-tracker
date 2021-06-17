package com.tracker.infrastructure.persistence.repository;

import com.tracker.infrastructure.persistence.entity.ItemEntity;
import com.tracker.infrastructure.persistence.entity.enums.DeliveryStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ItemRepository extends CrudRepository<ItemEntity, Long> {

    Optional<ItemEntity> findByTrackingId(String trackingId);

    @Query(value = "select i from ItemEntity i WHERE i.currentStatus = :status")
    Page<ItemEntity> getItems(@Param("status") DeliveryStatus status, Pageable pageable);
}
