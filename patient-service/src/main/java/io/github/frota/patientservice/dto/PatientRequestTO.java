package io.github.frota.patientservice.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import io.github.frota.patientservice.dto.validators.CreatePatientValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PatientRequestTO {

	@NotBlank
	@Size(max=100, message="cannot exceed {max} characters")
	private String name;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String address;
	
	@NotNull
	@PastOrPresent
	private LocalDate dateOfBirth;
	
	@NotNull(groups=CreatePatientValidationGroup.class)
	@PastOrPresent(groups=CreatePatientValidationGroup.class)
	private LocalDateTime registeredDate;

}
