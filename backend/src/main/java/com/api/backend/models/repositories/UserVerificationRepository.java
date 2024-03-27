package com.api.backend.models.repositories;

import com.api.backend.models.entities.UserVerification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVerificationRepository extends JpaRepository<UserVerification, Long> {
}
