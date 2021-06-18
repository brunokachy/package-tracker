package com.tracker.core.service.impl;

import com.tracker.core.domain.Item;
import com.tracker.core.utils.CodeGenerator;
import com.tracker.entrypoint.models.response.ItemResponse;
import com.tracker.infrastructure.persistence.entity.ItemEntity;
import com.tracker.infrastructure.persistence.entity.enums.DeliveryStatus;
import com.tracker.infrastructure.persistence.service.ItemPersistenceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegisterItemServiceImplTest {

    @Mock
    ItemPersistenceService itemPersistenceService;

    @InjectMocks
    RegisterItemServiceImpl registerItemService;

    @DisplayName("Test for registering item")
    @Test
    void registerItemTest() {

        Item item = Item.builder()
                .deliveryAddress("Ikeja")
                .description("Beer")
                .pickUpAddress("Ajah")
                .build();

        String trackingId = CodeGenerator.randomString(8).toUpperCase();

        ItemEntity itemEntity = ItemEntity.builder()
                .deliveryAddress(item.getDeliveryAddress())
                .description(item.getDescription())
                .trackingId(trackingId)
                .pickUpAddress(item.getPickUpAddress())
                .currentStatus(DeliveryStatus.CREATED)
                .build();
        itemEntity.setDateCreated(LocalDateTime.now());
        itemEntity.setDateModified(LocalDateTime.now());

        when(itemPersistenceService.saveItem(any())).thenReturn(itemEntity);

        ItemResponse response = registerItemService.registerItem(item);

        verify(itemPersistenceService, times(1)).saveItem(any());

        assertEquals(response.getTrackingId(), trackingId);
        assertEquals(response.getDeliveryAddress(), item.getDeliveryAddress());
        assertEquals(response.getCurrentStatus(), DeliveryStatus.CREATED.name());
    }
}
