package com.api.backend.models.services;

import com.api.backend.models.repositories.PortfolioImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortfolioImageServiceImpl implements PortfolioImageService {

    @Autowired
    private PortfolioImageRepository repo;
}
