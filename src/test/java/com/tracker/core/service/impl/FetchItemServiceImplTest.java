package com.tracker.core.service.impl;

import com.tracker.core.service.RegisterItemService;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FetchItemServiceImplTest {

    @Mock
    ItemPersistenceService itemPersistenceService;

    @Mock
    RegisterItemService registerItemService;

    @InjectMocks
    FetchItemServiceImpl fetchItemService;

//    @DisplayName("Test for fetching items by delivery status")
//    @Test
//    void fetchItemsByDeliveryStatusTest() {
//        DeliveryStatus status = DeliveryStatus.DELIVERED;
//        int page = 0;
//        int size = 10;
//
//        ItemEntity itemEntity = new ItemEntity();
//        ItemEntity itemEntity1 = new ItemEntity();
//        ItemEntity itemEntity2 = new ItemEntity();
//        List<ItemEntity> items = new ArrayList<>();
//        items.add(itemEntity);
//        items.add(itemEntity1);
//        items.add(itemEntity2);
//
//        Pageable pageable = PageRequest.of(page, size);
//
//        Page<ItemEntity> itemEntities = new PageImpl<>(items, pageable, 3);
//
//        ItemResponse response = new ItemResponse();
//
//        when(registerItemService.toResponse(any(ItemEntity.class))).thenReturn(response);
//        when(itemPersistenceService.getItems(any(), any(), any())).thenReturn(itemEntities);
//
//        verify(registerItemService, atLeast(1)).toResponse(any(ItemEntity.class));
//        verify(itemPersistenceService, times(1)).getItems(any(), any(), any());
//
////        assertEquals(enquiryResponse.getResponseCode(), responseCode);
////        assertEquals(enquiryResponse.getName(), "");
////        assertEquals(enquiryResponse.getAmountInKobo(), 0);
//    }
}
