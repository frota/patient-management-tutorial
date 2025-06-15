package io.github.frota.patientservice.exception;

public class PatientNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7707398596712325855L;
	
	public PatientNotFoundException(String message) {
		super(message);
	}

}
