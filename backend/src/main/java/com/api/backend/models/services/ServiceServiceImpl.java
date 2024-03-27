package com.api.backend.models.services;

import com.api.backend.models.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceServiceImpl implements ServiceService{

    @Autowired
    private ServiceRepository repo;
}
