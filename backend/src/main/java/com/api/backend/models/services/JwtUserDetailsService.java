package com.api.backend.models.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.backend.models.repositories.UserRepository;


@Service
public class JwtUserDetailsService implements UserDetailsService {

    
	@Autowired
	private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	com.api.backend.models.entities.User AuthUser = repo.findByEmail(username);
    	if(AuthUser != null) {
    		return new User(AuthUser.getEmail(), AuthUser.getPassword(),
                    new ArrayList<>());
    	}else {
    		throw new UsernameNotFoundException("User not found with username: " + username);
    	}
    }
}