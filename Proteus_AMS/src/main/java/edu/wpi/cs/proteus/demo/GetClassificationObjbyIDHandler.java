package edu.wpi.cs.proteus.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;

import edu.wpi.cs.proteus.db.ClassificationDAO;
import edu.wpi.cs.proteus.http.GetClassificationResponse;
import edu.wpi.cs.proteus.http.GetObjectsByID;
import edu.wpi.cs.proteus.model.Classification;

public class GetClassificationObjbyIDHandler implements RequestHandler<Object, GetClassificationResponse> {

	LambdaLogger logger;
	
	@Override
	public GetClassificationResponse handleRequest(Object input, Context context) {
		
		GetClassificationResponse response;
		Gson gson = new Gson();
		GetObjectsByID instance = gson.fromJson(input.toString(), GetObjectsByID.class);

		try {
			Classification classification = getClassificationByID(instance.getID());
			response = new GetClassificationResponse(classification, 200);
		}catch (Exception e) { 
			response = new GetClassificationResponse(400, "Unable to get classification(" + e.getMessage() + ")");
		}
		return response;
	}
	
	private Classification getClassificationByID(String ID) throws Exception {
		if (logger != null) { logger.log("in get Classification"); }
		ClassificationDAO dao = new ClassificationDAO();
		
		return dao.getClassificationByID(ID);
	}

}
