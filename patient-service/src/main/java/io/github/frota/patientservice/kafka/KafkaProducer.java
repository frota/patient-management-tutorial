package io.github.frota.patientservice.kafka;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import io.github.frota.patientservice.model.Patient;
import lombok.RequiredArgsConstructor;
import patient.events.PatientEvent;

@RequiredArgsConstructor
@Service
public class KafkaProducer {

	private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
	
	private static final String KAFKA_TOPIC = "patient";
	
	private final KafkaTemplate<String, byte[]> kafkaTemplate;
	
	public void sendEvent(Patient patient) {
		PatientEvent event = PatientEvent.newBuilder()
				.setPatientId(patient.getId().toString())
				.setName(patient.getName())
				.setEmail(patient.getEmail())
				.setEventType("PATIENT_CREATED") // like a "subcategory" within the topic
				.build();
		
		try {
			CompletableFuture<SendResult<String, byte[]>> future = kafkaTemplate.send(KAFKA_TOPIC, event.toByteArray());
			
			future.whenComplete((result, ex) -> {
				if (ex == null) {
					log.debug("Message sent: {}", result.getRecordMetadata());
				} else {
					log.error("Send failed: {}", ex.getMessage());
				}
			});
		} catch (Exception e) {
			log.error("Error sending PATIENT_CREATED event: {}", event, e);
		}
	}

}
