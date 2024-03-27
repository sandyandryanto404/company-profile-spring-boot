package com.api.backend.models.services;

import com.api.backend.models.repositories.FaqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FaqServiceImpl implements FaqService {

    @Autowired
    private FaqRepository repo;

}
