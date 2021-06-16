package com.tracker.core.service;

import com.tracker.core.domain.PackageModel;
import com.tracker.entrypoint.models.response.PackageResponse;
import com.tracker.infrastructure.persistence.entity.PackageEntity;

public interface RegisterPackageService {

    PackageResponse registerPackage(PackageModel request);

    PackageResponse toResponse(PackageEntity packageEntity);
}
