package com.tracker.core.service.impl;

import com.tracker.core.domain.Item;
import com.tracker.core.service.RegisterItemService;
import com.tracker.core.utils.CodeGenerator;
import com.tracker.entrypoint.models.response.ItemResponse;
import com.tracker.infrastructure.persistence.entity.ItemEntity;
import com.tracker.infrastructure.persistence.service.ItemPersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class RegisterItemServiceImpl implements RegisterItemService {

    private final ItemPersistenceService itemPersistenceService;

    @Override
    public ItemResponse registerItem(Item request) {

        ItemEntity itemEntity = ItemEntity.builder()
                .deliveryAddress(request.getDeliveryAddress())
                .description(request.getDescription())
                .trackingId(CodeGenerator.randomString(8).toUpperCase())
                .pickUpAddress(request.getPickUpAddress())
                .build();

        itemEntity = itemPersistenceService.saveItem(itemEntity);

        return toResponse(itemEntity);
    }

    @Override
    public ItemResponse toResponse(ItemEntity itemEntity) {

        ItemResponse response = new ItemResponse();
        response.setDateCreated(itemEntity.getDateCreated().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        response.setCurrentStatus(itemEntity.getCurrentStatus().name());
        response.setDateUpdated(itemEntity.getDateModified().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        response.setDescription(itemEntity.getDescription());
        response.setDeliveryAddress(itemEntity.getDeliveryAddress());
        response.setTrackingId(itemEntity.getTrackingId());
        response.setPickUpAddress(itemEntity.getPickUpAddress());

        return response;
    }


}
