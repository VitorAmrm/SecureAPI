package com.amorim.vitor.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amorim.vitor.entity.User;
import com.amorim.vitor.entity.request.LoginRequest;
import com.amorim.vitor.service.UserService;

@RestController
@CrossOrigin
public class LoginController {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private UserService service;

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<?> generateAuthenticationToken(@RequestBody LoginRequest authenticationRequest)
			throws Exception {
		return ResponseEntity.ok().body(service.login(authenticationRequest));
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<?> create (@RequestBody User user) {
		return ResponseEntity.ok().body(service.createUser(user));
		
	}
	

}
