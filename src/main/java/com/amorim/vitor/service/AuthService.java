package com.amorim.vitor.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.amorim.vitor.entity.User;
import com.amorim.vitor.repository.UserRepository;


@Service
public class AuthService implements UserDetailsService {
	
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		User apiUser = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuario com nome : "+username+" não encontrado"));
        return new org.springframework.security.core.userdetails.User(apiUser.getUsername(), apiUser.getPassword(), Collections.emptyList());
		
	}
	
}
