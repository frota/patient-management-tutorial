package io.github.frota.patientservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.frota.patientservice.dto.PatientRequestTO;
import io.github.frota.patientservice.dto.PatientResponseTO;
import io.github.frota.patientservice.dto.validators.CreatePatientValidationGroup;
import io.github.frota.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;

@Tag(name="patient", description="API for managing patients")
@RequiredArgsConstructor
@RestController
@RequestMapping("/patients")
public class PatientController {

	private final PatientService patientService;
	
	@GetMapping
	@Operation(summary="Get patients")
	public ResponseEntity<List<PatientResponseTO>> getPatients() {
		var patients = patientService.getPatients();
		return ResponseEntity.ok(patients);
	}
	
	@PostMapping
	@Operation(summary="Create a new patient")
	public ResponseEntity<PatientResponseTO> createPatient(
			@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestTO patientRequestTO) {
		var patientResponseTO = patientService.createPatient(patientRequestTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(patientResponseTO);
	}
	
	@PutMapping("/{id}")
	@Operation(summary="Update a patient")
	public ResponseEntity<PatientResponseTO> updatePatient(
			@PathVariable Long id,
			@Validated(Default.class) @RequestBody PatientRequestTO patientRequestTO) {
		var patientResponseTO = patientService.updatePatient(id, patientRequestTO);
		return ResponseEntity.ok(patientResponseTO);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary="Delete a patient")
	public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
		patientService.deletePatient(id);
		return ResponseEntity.noContent().build();
	}

}
