package io.github.frota.patientservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.frota.patientservice.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

	Optional<Patient> findByEmail(String email);
	
	boolean existsByEmail(String email);
	
	boolean existsByEmailAndIdNot(String email, Long id);

}
