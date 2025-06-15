package io.github.frota.authservice.service;

import io.github.frota.authservice.model.User;
import io.github.frota.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
