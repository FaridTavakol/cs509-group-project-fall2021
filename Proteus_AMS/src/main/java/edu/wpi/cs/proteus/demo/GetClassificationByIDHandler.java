package edu.wpi.cs.proteus.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.proteus.db.ClassificationDAO;
import edu.wpi.cs.proteus.http.GetClassificationByIdRequest;
import edu.wpi.cs.proteus.http.GetClassificationByIdResponse;
import edu.wpi.cs.proteus.model.Classification;


public class GetClassificationByIDHandler implements RequestHandler<GetClassificationByIdRequest,GetClassificationByIdResponse> {
	
	LambdaLogger logger;
	
	@Override
	public GetClassificationByIdResponse handleRequest(GetClassificationByIdRequest input, Context context) {
		
		GetClassificationByIdResponse response;
		
		try {
			Classification classification = getClassification(input.getClassificationId());
			response = new GetClassificationByIdResponse(classification.getClassificationName());
		}catch (Exception e) { 
			response = new GetClassificationByIdResponse("400", "Unable to get classification(" + e.getMessage() + ")");
		}
		return response;
	}
	
	private Classification getClassification(String id) throws Exception {
		if (logger != null) { logger.log("in addClassification"); }
		ClassificationDAO dao = new ClassificationDAO();
		
		return dao.getClassificationByID(id);
	}

}
