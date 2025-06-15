package io.github.frota.patientservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.frota.patientservice.dto.PatientRequestTO;
import io.github.frota.patientservice.dto.PatientResponseTO;
import io.github.frota.patientservice.exception.EmailAlreadyExistsException;
import io.github.frota.patientservice.exception.PatientNotFoundException;
import io.github.frota.patientservice.grpc.BillingServiceGrpcClient;
import io.github.frota.patientservice.kafka.KafkaProducer;
import io.github.frota.patientservice.mapper.PatientMapper;
import io.github.frota.patientservice.model.Patient;
import io.github.frota.patientservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PatientService {

	private final PatientRepository patientRepository;
	private final PatientMapper patientMapper;
	private final BillingServiceGrpcClient billingServiceGrpcClient;
	private final KafkaProducer kafkaProducer;
	
	public List<PatientResponseTO> getPatients() {
		List<Patient> patients = patientRepository.findAll();
		
		return patients.stream()
				.map(patientMapper::toDTO)
				.toList();
	}
	
	public PatientResponseTO createPatient(PatientRequestTO patientRequestTO) {
		final String email = patientRequestTO.getEmail();
		if (patientRepository.existsByEmail(email))
			throw new EmailAlreadyExistsException("A patient with this email already exists " + email);
		
		Patient newPatient = patientRepository.save(patientMapper.toModel(patientRequestTO));
		
		billingServiceGrpcClient.createBillingAccount(
				newPatient.getId().toString(),
				newPatient.getName(),
				newPatient.getEmail());
		
		kafkaProducer.sendEvent(newPatient);
		
		return patientMapper.toDTO(newPatient);
	}
	
	public PatientResponseTO updatePatient(Long id, PatientRequestTO patientRequestTO) {
		Patient patient = patientRepository.findById(id)
				.orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + id));
		
		final String email = patientRequestTO.getEmail();
		if (patientRepository.existsByEmailAndIdNot(email, id))
			throw new EmailAlreadyExistsException("A patient with this email already exists " + email);
		
		patient.setName(patientRequestTO.getName());
		patient.setEmail(patientRequestTO.getEmail());
		patient.setAddress(patientRequestTO.getAddress());
		patient.setDateOfBirth(patientRequestTO.getDateOfBirth());
		
		Patient updatedPatient = patientRepository.save(patient);
		return patientMapper.toDTO(updatedPatient);
	}
	
	public void deletePatient(Long id) {
		patientRepository.deleteById(id);
	}

}
