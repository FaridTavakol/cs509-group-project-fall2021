package edu.wpi.cs.proteus.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.proteus.db.ClassificationDAO;
import edu.wpi.cs.proteus.http.GetClassificationRequest;
import edu.wpi.cs.proteus.http.GetClassificationResponse;
import edu.wpi.cs.proteus.model.Classification;


public class GetClassificationHandler implements RequestHandler<GetClassificationRequest,GetClassificationResponse> {
	
	LambdaLogger logger;
	
	@Override
	public GetClassificationResponse handleRequest(GetClassificationRequest input, Context context) {
		
		GetClassificationResponse response;
		
		try {
			Classification classification = getClassification(input.getClassificationName());
			response = new GetClassificationResponse(classification, 200);
		}catch (Exception e) { 
			response = new GetClassificationResponse(400, "Unable to get classification(" + e.getMessage() + ")");
		}
		return response;
	}
	
	private Classification getClassification(String name) throws Exception {
		if (logger != null) { logger.log("in addClassification"); }
		ClassificationDAO dao = new ClassificationDAO();
		
		return dao.getClassification(name);
	}

}
