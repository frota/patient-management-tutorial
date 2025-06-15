package io.github.frota.patientservice.mapper;

import org.springframework.stereotype.Component;

import io.github.frota.patientservice.dto.PatientRequestTO;
import io.github.frota.patientservice.dto.PatientResponseTO;
import io.github.frota.patientservice.model.Patient;

@Component
public class PatientMapper {

	public PatientResponseTO toDTO(Patient patient) {
		PatientResponseTO dto = new PatientResponseTO();
		dto.setId(patient.getId());
		dto.setName(patient.getName());
		dto.setEmail(patient.getEmail());
		dto.setAddress(patient.getAddress());
		dto.setDateOfBirth(patient.getDateOfBirth().toString());
		
		return dto;
	}
	
	public Patient toModel(PatientRequestTO patientRequestTO) {
		Patient model = new Patient();
		model.setName(patientRequestTO.getName());
		model.setEmail(patientRequestTO.getEmail());
		model.setAddress(patientRequestTO.getAddress());
		model.setDateOfBirth(patientRequestTO.getDateOfBirth());
		model.setRegisteredDate(patientRequestTO.getRegisteredDate());
		
		return model;
	}

}
