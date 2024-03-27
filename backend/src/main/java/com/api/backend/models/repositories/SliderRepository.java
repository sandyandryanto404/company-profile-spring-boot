package com.api.backend.models.repositories;

import com.api.backend.models.entities.Slider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SliderRepository extends JpaRepository<Slider, Long> {
}
