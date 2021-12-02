package edu.wpi.cs.proteus.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import edu.wpi.cs.proteus.db.ClassificationDAO;
import edu.wpi.cs.proteus.http.RemoveClassificationRequest;
import edu.wpi.cs.proteus.http.RemoveClassificationResponse;
import edu.wpi.cs.proteus.model.Classification;

public class RemoveClassificationHandler implements RequestHandler<RemoveClassificationRequest,RemoveClassificationResponse> {
	
	LambdaLogger logger;
	
	@Override
	public RemoveClassificationResponse handleRequest(RemoveClassificationRequest input, Context context) {
		
		RemoveClassificationResponse response;
		
		try {
			if (removeClassification(input.getClassificationName())) {
				response = new RemoveClassificationResponse(input.getClassificationName());
			} else {
				response = new RemoveClassificationResponse(input.getClassificationName(), 422);
			}
		}catch (Exception e) { 
			response = new RemoveClassificationResponse("Unable to add classification: " + input.getClassificationName()+ " (" + e.getMessage() + ")", 400);
		}
		return response;
	}
	
	boolean removeClassification(String name) throws Exception {
		if (logger != null) { logger.log("in removeClassification"); }
		ClassificationDAO dao = new ClassificationDAO();
		
		System.out.println("removeClassification");
		Classification classification = dao.getClassification(name);
		return dao.deleteClassification(classification);
	}

}
