package io.github.frota.authservice.service;

import io.github.frota.authservice.dto.LoginRequestTO;
import io.github.frota.authservice.util.JwtUtils;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {

	private static final Logger log = LoggerFactory.getLogger(AuthService.class);
	
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtils jwtUtils;
	
	public Optional<String> authenticate(LoginRequestTO loginRequestTO) {
		Optional<String> token = userService
				.findByEmail(loginRequestTO.getEmail())
				.filter(u -> passwordEncoder.matches(loginRequestTO.getPassword(), u.getPassword()))
				.map(u -> jwtUtils.generateToken(u.getEmail(), u.getRole()));
		
		return token;
	}
	
	public boolean validateToken(String token) {
		try {
			jwtUtils.validateToken(token);
			return true;
		} catch (JwtException e) {
			log.error("JWT validation failed: {}", e.getMessage());
			return false;
		}
	}

}
