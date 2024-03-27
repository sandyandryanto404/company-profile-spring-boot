package com.api.backend.models.repositories;

import com.api.backend.models.entities.Faq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqRepository extends JpaRepository<Faq, Long> {
}
