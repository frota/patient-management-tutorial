package io.github.frota.billingservice.grpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import billing.BillingResponse;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class BillingGrpcService extends BillingServiceImplBase {

	private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);
	
	@Override
	public void createBillingAccount(
			billing.BillingRequest request,
			StreamObserver<billing.BillingResponse> responseObserver) {
		log.info("createBillingAccount request received:\n{}", request);
		
		// Business login - e.g save to database, perform calculations, ...
		
		var response = BillingResponse.newBuilder() // mocked for now
				.setAccountId("12345")
				.setStatus("ACTIVE")
				.build();
		
		responseObserver.onNext(response); // send response
		responseObserver.onCompleted(); // response is completed
	}

}
