package com.tracker.core.domain;

import com.tracker.infrastructure.persistence.entity.enums.DeliveryStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PackageModel {

    private String trackingId;

    private String description;

    private DeliveryStatus currentStatus;

    private String pickUpAddress;

    private String deliveryAddress;

    private LocalDateTime dateCreated;

    private LocalDateTime dateModified;
}
