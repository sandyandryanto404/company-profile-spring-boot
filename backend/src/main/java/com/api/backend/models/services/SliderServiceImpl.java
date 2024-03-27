package com.api.backend.models.services;

import com.api.backend.models.repositories.SliderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SliderServiceImpl implements SliderService {

    @Autowired
    private SliderRepository repo;
}
