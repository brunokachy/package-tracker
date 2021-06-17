package com.tracker.core.service;

import com.tracker.entrypoint.models.response.DeliveryStatusResponse;

import java.util.List;

public interface TrackItemService {

    DeliveryStatusResponse getItemDeliveryStatus(String trackingId);

    List<DeliveryStatusResponse> getItemStatusHistory(String trackingId);
}
