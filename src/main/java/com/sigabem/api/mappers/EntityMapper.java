package com.sigabem.api.mappers;

import com.sigabem.api.models.entities.BaseEntity;

public interface EntityMapper<E extends BaseEntity, D> {
    E convertToEntity(D dto);
}
