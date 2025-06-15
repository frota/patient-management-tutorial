package io.github.frota.authservice.controller;

import io.github.frota.authservice.dto.LoginRequestTO;
import io.github.frota.authservice.dto.LoginResponseTO;
import io.github.frota.authservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class AuthController {

	private final AuthService authService;
	
	@PostMapping("/login")
	@Operation(summary="Generate token on user login")
	public ResponseEntity<LoginResponseTO> login(@Valid @RequestBody LoginRequestTO loginRequestTO) {
		Optional<String> tokenOptional = authService.authenticate(loginRequestTO);
		if (tokenOptional.isEmpty())
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		
		String token = tokenOptional.get();
		return ResponseEntity.ok(new LoginResponseTO(token));
	}
	
	@GetMapping("/validate")
	@Operation(summary="Validate token")
	public ResponseEntity<Void> validateToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
		if (StringUtils.isEmpty(authHeader) || !authHeader.startsWith("Bearer "))
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		
		return authService.validateToken(authHeader.substring(7))
				? ResponseEntity.ok().build()
				: ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

}
