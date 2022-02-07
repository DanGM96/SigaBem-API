package com.sigabem.api.repositories;

import com.sigabem.api.models.entities.FreteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreteRepository extends CrudRepository<FreteEntity, Long> {
}
