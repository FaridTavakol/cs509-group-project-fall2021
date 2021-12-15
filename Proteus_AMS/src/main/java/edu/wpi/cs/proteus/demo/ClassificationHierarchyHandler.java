package edu.wpi.cs.proteus.demo;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.proteus.db.ClassificationDAO;
import edu.wpi.cs.proteus.http.AllClassificationsResponse;
import edu.wpi.cs.proteus.http.ClassificationHierarchyResponse;
import edu.wpi.cs.proteus.model.Classification;

public class ClassificationHierarchyHandler implements RequestHandler<Object,ClassificationHierarchyResponse> {
	
	LambdaLogger logger;
	
	@Override
	public ClassificationHierarchyResponse handleRequest(Object input, Context context) {
		
		ClassificationHierarchyResponse response;
		
		try {
			List<Classification> classifications = getAllChildClassifications();
			response = new ClassificationHierarchyResponse(classifications, 200);
		}catch (Exception e) { 
			response = new ClassificationHierarchyResponse(400, "Unable to get all classifications(" + e.getMessage() + ")");
		}
		return response;
	}
	
	private List<Classification> getAllChildClassifications() throws Exception {
		if (logger != null) { logger.log("in addClassification"); }
		ClassificationDAO dao = new ClassificationDAO();
		
		return dao.getAllChildClassifications();
	}

}
