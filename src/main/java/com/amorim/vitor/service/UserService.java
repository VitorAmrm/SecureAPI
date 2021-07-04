
package com.amorim.vitor.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.amorim.vitor.entity.User;
import com.amorim.vitor.entity.request.LoginRequest;
import com.amorim.vitor.entity.response.LoginResponse;
import com.amorim.vitor.repository.UserRepository;
import com.amorim.vitor.utils.JwtUtils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
	
	private final UserRepository repository;
	private final BCryptPasswordEncoder encoder;
	private AuthService jwtAuthService;
	private JwtUtils jwt;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public LoginResponse login(LoginRequest user) throws Exception  {
		
		authenticate(user.getUsername(), user.getPassword());

		final UserDetails userDetails = jwtAuthService
				.loadUserByUsername(user.getUsername());

		final String token = jwt.generateToken(userDetails);

		return new LoginResponse(token, userDetails.getUsername());
	}

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	public User createUser (User user) {
		User nUser = new User();
		Optional<User> found = repository.findByUsername(user.getUsername());
		if(found.isPresent()) {
			throw new RuntimeException("User Already registered.");
		}
		nUser.setUsername(user.getUsername());
		nUser.setPassword(encoder.encode(user.getPassword()));
		return repository.save(nUser);
	}
	
	public List<User> list(){
		return repository.findAll();
	}
}
