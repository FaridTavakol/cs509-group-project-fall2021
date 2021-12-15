package edu.wpi.cs.proteus.demo;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;

import edu.wpi.cs.proteus.db.ImplementationsDAO;
import edu.wpi.cs.proteus.http.AllImplementationsResponse;
import edu.wpi.cs.proteus.http.GetObjectsByID;
import edu.wpi.cs.proteus.model.Implementation;

public class GetImplementationByAlgoHandler implements RequestHandler<Object, AllImplementationsResponse> {

	LambdaLogger logger;
    @Override
    public AllImplementationsResponse handleRequest(Object input, Context context) {
        context.getLogger().log("Input: " + input);

		AllImplementationsResponse response;

		Gson gson = new Gson();
		GetObjectsByID instance = gson.fromJson(input.toString(), GetObjectsByID.class);

		try {
			List<Implementation> pinstances = getImplementation(instance.getID());
					
			response = new AllImplementationsResponse(pinstances, 200);
		} catch (Exception e) {
			response = new AllImplementationsResponse(400, "Unable to get Implementations by Algo ID(" + e.getMessage() + ")");
		}
		return response;
	}

	List<Implementation> getImplementation(String algoID) throws Exception {
		if (logger != null) {
			logger.log("in get Implementations");
		}
		ImplementationsDAO dao = new ImplementationsDAO();
		System.out.println("connected to DB");

		return dao.getAllImplementations(algoID);
	}
}