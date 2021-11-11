package edu.wpi.cs.proteus.demo;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.proteus.db.ClassificationDAO;
import edu.wpi.cs.proteus.http.AllClassificationsResponse;
import edu.wpi.cs.proteus.model.Classification;

public class AllClassificationsHandler implements RequestHandler<Object,AllClassificationsResponse> {
	
	LambdaLogger logger;
	
	@Override
	public AllClassificationsResponse handleRequest(Object input, Context context) {
		
		AllClassificationsResponse response;
		
		try {
			List<Classification> classifications = getAllClassifications();
			response = new AllClassificationsResponse(classifications, 200);
		}catch (Exception e) { 
			response = new AllClassificationsResponse(400, "Unable to get all classifications(" + e.getMessage() + ")");
		}
		return response;
	}
	
	private List<Classification> getAllClassifications() throws Exception {
		if (logger != null) { logger.log("in addClassification"); }
		ClassificationDAO dao = new ClassificationDAO();
		
		return dao.getAllClassifications();
	}
}
