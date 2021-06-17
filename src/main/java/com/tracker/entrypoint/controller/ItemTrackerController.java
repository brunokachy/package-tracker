package com.tracker.entrypoint.controller;

import com.tracker.core.domain.PagedData;
import com.tracker.core.service.FetchItemService;
import com.tracker.core.service.RegisterItemService;
import com.tracker.core.service.TrackItemService;
import com.tracker.core.service.UpdateItemService;
import com.tracker.entrypoint.models.request.RegisterItemRequest;
import com.tracker.entrypoint.models.response.ApiResponse;
import com.tracker.entrypoint.models.response.DeliveryStatusResponse;
import com.tracker.entrypoint.models.response.ItemResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@Api(tags = "Item Tracking Management Endpoints")
@RequestMapping(value = "/api/v1/item-tracker/", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemTrackerController {

    private final RegisterItemService registerItemService;
    private final UpdateItemService updateItemService;
    private final TrackItemService trackItemService;
    private final FetchItemService fetchItemService;


    @ApiOperation(value = "Register new item for pick up")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<ItemResponse>> registerItem(@RequestBody @Valid RegisterItemRequest request) {

        ItemResponse response = registerItemService.registerItem(request.toDomain());

        ApiResponse<ItemResponse> apiResponse = new ApiResponse<>("Processed successfully.", response);

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update Item Delivery Status")
    @PutMapping(value = "{trackingId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<DeliveryStatusResponse>> updateDeliveryStatus(@PathVariable(value = "trackingId") String trackingId,
                                                                                    @RequestBody @Valid UpdateDeliveryStatusRequest request) {

        DeliveryStatusResponse response = updateItemService.updateDeliveryStatus(trackingId, request.getStatus());

        ApiResponse<DeliveryStatusResponse> apiResponse = new ApiResponse<>("Processed successfully.", response);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "Returns paginated items for delivery")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<PagedData<ItemResponse>>> fetchItems(@ApiParam(value = "Delivery Status: CREATED, PICKED_UP, IN_TRANSIT, WAREHOUSE, DELIVERED, CANCELLED")
                                                                                 @Valid @Pattern(regexp = "(CREATED|PICKED_UP|IN_TRANSIT|WAREHOUSE|DELIVERED|CANCELLED)")
                                                                                 @RequestParam(value = "deliveryStatus", defaultValue = "CREATED") String deliveryStatus,
                                                                              @ApiParam(value = "No. of records per page. Min:1, Max:20") @Valid @Min(value = 1) @Max(value = 20) @RequestParam("size") int size,
                                                                              @ApiParam(value = "The index of the page to return. Min: 0") @Valid @Min(value = 0) @RequestParam("page") int page
    ) {
        PagedData<ItemResponse> response = fetchItemService.getPagedItems(deliveryStatus, page, size);

        ApiResponse<PagedData<ItemResponse>> apiResponse = new ApiResponse<>("Processed successfully.", response);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "Current item delivery status")
    @GetMapping(value = "{trackingId}/status")
    public ResponseEntity<ApiResponse<DeliveryStatusResponse>> getItemStatus(@PathVariable(value = "trackingId") String trackingId) {

        DeliveryStatusResponse response = trackItemService.getItemDeliveryStatus(trackingId);

        ApiResponse<DeliveryStatusResponse> apiResponseJSON = new ApiResponse<>("Processed successfully.", response);

        return new ResponseEntity<>(apiResponseJSON, HttpStatus.OK);
    }

    @ApiOperation(value = "Item delivery status history")
    @GetMapping(value = "{trackingId}/status/history")
    public ResponseEntity<ApiResponse<List<DeliveryStatusResponse>>> getItemStatusHistory(@PathVariable(value = "trackingId") String trackingId) {

        List<DeliveryStatusResponse> response = trackItemService.getItemStatusHistory(trackingId);

        ApiResponse<List<DeliveryStatusResponse>> apiResponseJSON = new ApiResponse<>("Processed successfully.", response);

        return new ResponseEntity<>(apiResponseJSON, HttpStatus.OK);
    }

    @Data
    private static class UpdateDeliveryStatusRequest {
        @ApiModelProperty(notes = "New Delivery Status.", required = true)
        @Pattern(regexp = "(PICKED_UP|IN_TRANSIT|WAREHOUSE|DELIVERED|CANCELLED)")
        @NotEmpty
        private String status;
    }

}
