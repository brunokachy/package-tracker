package com.tracker.core.service;

import com.tracker.entrypoint.models.response.DeliveryStatusResponse;

public interface UpdateItemService {

    DeliveryStatusResponse updateDeliveryStatus(String trackingId, String status);
}
