package com.tracker.core.service;

import com.tracker.entrypoint.models.response.DeliveryStatusResponse;

public interface UpdatePackageService {

    DeliveryStatusResponse updateDeliveryStatus(String trackingId, String status);
}
