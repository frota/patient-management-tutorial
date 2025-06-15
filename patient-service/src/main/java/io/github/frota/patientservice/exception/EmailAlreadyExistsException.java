package io.github.frota.patientservice.exception;

public class EmailAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 8681663889242063104L;
	
	public EmailAlreadyExistsException(String message) {
		super(message);
	}

}
