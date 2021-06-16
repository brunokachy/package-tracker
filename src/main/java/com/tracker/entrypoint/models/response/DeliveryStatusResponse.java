package com.tracker.entrypoint.models.response;

import lombok.Data;

@Data
public class DeliveryStatusResponse {


    private String trackingId;

    private String currentStatus;

    private String dateUpdated;
}
