package io.github.frota.authservice.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class LoginResponseTO {

	private final String token;

}
