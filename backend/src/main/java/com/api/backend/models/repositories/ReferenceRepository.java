package com.api.backend.models.repositories;

import com.api.backend.models.entities.Reference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReferenceRepository extends JpaRepository<Reference, Long> {
}
