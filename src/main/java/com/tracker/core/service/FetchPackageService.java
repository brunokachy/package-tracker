package com.tracker.core.service;

import com.tracker.core.domain.PagedData;
import com.tracker.entrypoint.models.response.PackageResponse;

public interface FetchPackageService {

    PagedData<PackageResponse> getPagedPackages(String deliveryStatus, int page, int size);
}
