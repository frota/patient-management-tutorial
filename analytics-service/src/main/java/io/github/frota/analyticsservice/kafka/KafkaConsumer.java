package io.github.frota.analyticsservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.protobuf.InvalidProtocolBufferException;

import lombok.RequiredArgsConstructor;
import patient.events.PatientEvent;

@RequiredArgsConstructor
@Service
public class KafkaConsumer {

	private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);
	
	private static final String KAFKA_TOPIC = "patient";
	
	@KafkaListener(topics=KAFKA_TOPIC, groupId="analytics-service")
	public void consumeEvent(byte[] event) {
		try {
			PatientEvent patientEvent = PatientEvent.parseFrom(event);
			// TODO Perform any business logic related to analytics here...
			
			log.info("Received Patient Event: [ PatientId = {}, PatientName = {}, PatientEmail = {} ]",
					patientEvent.getPatientId(), patientEvent.getName(), patientEvent.getEmail());
		} catch (InvalidProtocolBufferException e) {
			log.error("Error deserializing event: {}", e.getMessage());
		}
	}

}
