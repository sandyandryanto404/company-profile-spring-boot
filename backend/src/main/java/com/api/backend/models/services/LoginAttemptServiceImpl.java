package com.api.backend.models.services;

import com.api.backend.models.repositories.LoginAttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginAttemptServiceImpl implements  LoginAttemptService {

    @Autowired
    private LoginAttemptRepository repo;

}
