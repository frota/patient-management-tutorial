package io.github.frota.authservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequestTO {

	@NotNull(message="is required")
	@Email(message="should be a valid e-mail address")
	private String email;
	
	@NotNull(message="is required")
	@Size(min=8, message="must be at least {min} characters long")
	private String password;

}
