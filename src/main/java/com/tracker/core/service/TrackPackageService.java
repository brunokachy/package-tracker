package com.tracker.core.service;

import com.tracker.entrypoint.models.response.DeliveryStatusResponse;

import java.util.List;

public interface TrackPackageService {

    DeliveryStatusResponse getPackageStatus(String trackingId);

    List<DeliveryStatusResponse> getPackageStatusHistory(String trackingId);
}
