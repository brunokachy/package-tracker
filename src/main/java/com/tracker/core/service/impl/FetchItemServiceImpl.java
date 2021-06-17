package com.tracker.core.service.impl;

import com.tracker.core.domain.PagedData;
import com.tracker.core.service.FetchItemService;
import com.tracker.core.service.RegisterItemService;
import com.tracker.entrypoint.models.response.ItemResponse;
import com.tracker.infrastructure.persistence.entity.ItemEntity;
import com.tracker.infrastructure.persistence.entity.enums.DeliveryStatus;
import com.tracker.infrastructure.persistence.service.ItemPersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FetchItemServiceImpl implements FetchItemService {

    private final ItemPersistenceService itemPersistenceService;
    private final RegisterItemService registerItemService;

    @Override
    public PagedData<ItemResponse> getPagedItems(String deliveryStatus, int page, int size) {

        DeliveryStatus status = DeliveryStatus.valueOf(deliveryStatus);

        Page<ItemEntity> itemPage = itemPersistenceService.getItems(page, size, status);

        PagedData<ItemResponse> response = new PagedData<>();
        response.setRecords(itemPage.stream().map(registerItemService::toResponse).collect(Collectors.toList()));
        response.setTotalRecords(itemPage.getTotalElements());
        response.setTotalPages(itemPage.getTotalPages());

        return response;
    }
}
