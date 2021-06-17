package com.tracker.infrastructure.persistence.entity;

import com.tracker.infrastructure.persistence.entity.enums.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item")
public class ItemEntity extends AbstractBaseEntity<Long>{

    @Column(nullable = false, updatable = false, unique = true)
    private String trackingId;

    @Column(nullable = false)
    private String description;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus currentStatus = DeliveryStatus.CREATED;

    @Column(nullable = false)
    private String pickUpAddress;

    @Column(nullable = false)
    private String deliveryAddress;
}
