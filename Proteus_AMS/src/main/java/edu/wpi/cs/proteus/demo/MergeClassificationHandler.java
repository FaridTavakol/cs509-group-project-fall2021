package edu.wpi.cs.proteus.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.proteus.db.ClassificationDAO;
import edu.wpi.cs.proteus.http.MergeClassificationRequest;
import edu.wpi.cs.proteus.http.MergeClassificationResponse;
import edu.wpi.cs.proteus.model.Classification;

public class MergeClassificationHandler implements RequestHandler<MergeClassificationRequest,MergeClassificationResponse>{
	
	LambdaLogger logger;
	
	@Override
	public MergeClassificationResponse handleRequest(MergeClassificationRequest input, Context context) {
		
		MergeClassificationResponse response;
		
		try {
			if (mergeClassification(input.getClassificationOne(), input.getClassificationTwo())) {
				response = new MergeClassificationResponse(input.getClassificationOne());
			} else {
				response = new MergeClassificationResponse(input.getClassificationOne(), 422);
			}
		}catch (Exception e) { 
			response = new MergeClassificationResponse("Unable to merge classification: " + input.getClassificationOne() + ", " + input.getClassificationTwo() + "(" + e.getMessage() + ")", 400);
		}
		return response;
	}
	
	boolean mergeClassification(String one, String two) throws Exception {
		if (logger != null) { logger.log("in MergeClassification"); }
		ClassificationDAO dao = new ClassificationDAO();
		System.out.println("connected to DB");
		
		// check if present
		Classification exist1 = dao.getClassification(one);
		Classification exist2 = dao.getClassification(two);
		if (exist1 != null && exist2 != null) {
			System.out.println("handler - merge classification");
			return dao.mergeClassification(one, two);
		} else {
			return false;
		} 
	}
}
