package com.tracker.entrypoint.models.request;

import com.tracker.core.domain.PackageModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RegisterPackageRequest {

    @ApiModelProperty(notes = "A short description of the package", required = true)
    @NotEmpty
    private String description;

    @ApiModelProperty(notes = "Address to pick up the package", required = true)
    private String pickupAddress;

    @ApiModelProperty(notes = "Address to deliver the package", required = true)
    @NotEmpty
    private String deliveryAddress;

    public PackageModel toDomain() {
        return PackageModel.builder()
                .description(description)
                .deliveryAddress(deliveryAddress)
                .pickUpAddress(pickupAddress)
                .build();
    }

}
