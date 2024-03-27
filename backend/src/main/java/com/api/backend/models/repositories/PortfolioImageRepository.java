package com.api.backend.models.repositories;

import com.api.backend.models.entities.PortfolioImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioImageRepository extends JpaRepository<PortfolioImage, Long> {
}
