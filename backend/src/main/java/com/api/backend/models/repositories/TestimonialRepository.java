package com.api.backend.models.repositories;

import com.api.backend.models.entities.Testimonial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestimonialRepository extends JpaRepository<Testimonial, Long> {
}
