package edu.wpi.cs.proteus.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import edu.wpi.cs.proteus.db.ClassificationDAO;
import edu.wpi.cs.proteus.http.AddClassificationRequest;
import edu.wpi.cs.proteus.http.AddClassificationResponse;
import edu.wpi.cs.proteus.model.Classification;

public class AddClassificationHandler implements RequestHandler<AddClassificationRequest,AddClassificationResponse> {
	
	LambdaLogger logger;
	
	@Override
	public AddClassificationResponse handleRequest(AddClassificationRequest input, Context context) {
		
		AddClassificationResponse response;
		
		try {
			if (addClassification(input.getClassificationName(), input.getSuperClassification())) {
				response = new AddClassificationResponse(input.getClassificationName());
			} else {
				response = new AddClassificationResponse(input.getClassificationName(), 422);
			}
		}catch (Exception e) { 
			response = new AddClassificationResponse("Unable to add classification: " + input.getClassificationName()+ " (" + e.getMessage() + ")", 400);
		}
		return response;
	}
	
	boolean addClassification(String name, String superClass) throws Exception {
		if (logger != null) { logger.log("in addClassification"); }
		ClassificationDAO dao = new ClassificationDAO();
		System.out.println("connected to DB");
		
		// check if present
		Classification exist = dao.getClassification(name);
		System.out.println("checked exist");
		if (exist == null) {
			System.out.println("handler - add classification");
			return dao.addClassification(name, superClass);
		} else {
			return false;
		}
	}

}
