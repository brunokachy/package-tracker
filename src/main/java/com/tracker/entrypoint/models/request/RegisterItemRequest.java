package com.tracker.entrypoint.models.request;

import com.tracker.core.domain.Item;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RegisterItemRequest {

    @ApiModelProperty(notes = "A short description of the item", required = true)
    @NotEmpty
    private String description;

    @ApiModelProperty(notes = "Address to pick up the item", required = true)
    private String pickupAddress;

    @ApiModelProperty(notes = "Address to deliver the item", required = true)
    @NotEmpty
    private String deliveryAddress;

    public Item toDomain() {
        return Item.builder()
                .description(description)
                .deliveryAddress(deliveryAddress)
                .pickUpAddress(pickupAddress)
                .build();
    }

}
