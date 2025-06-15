package io.github.frota.patientservice.dto;

import lombok.Data;

@Data
public class PatientResponseTO {

	private Long id;
	private String name;
	private String email;
	private String address;
	private String dateOfBirth;

}
