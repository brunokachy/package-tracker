package com.tracker.entrypoint.models.response;

import lombok.Data;

@Data
public class PackageResponse {

    private String trackingId;

    private String description;

    private String currentStatus;

    private String pickUpAddress;

    private String deliveryAddress;

    private String dateCreated;

    private String dateUpdated;
}
