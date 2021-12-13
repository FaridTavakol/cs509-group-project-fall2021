package edu.wpi.cs.proteus.demo;

import java.io.StringReader;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;

import edu.wpi.cs.proteus.db.PIDAO;
import edu.wpi.cs.proteus.http.AllPIResponse;
import edu.wpi.cs.proteus.http.GetObjectsByID;

import edu.wpi.cs.proteus.model.PI;

public class GetPIHandler implements RequestHandler<Object, AllPIResponse> {

	LambdaLogger logger;

	@Override
	public AllPIResponse handleRequest(Object input, Context context) {
		context.getLogger().log("Input: " + input);

		AllPIResponse response;

		Gson gson = new Gson();
		GetObjectsByID instance = gson.fromJson(input.toString(), GetObjectsByID.class);

		try {
			List<PI> pinstances = getPI(instance.getID());
			for(int i=0;i<pinstances.size();i++)
				pinstances.get(i).setAlgorithmID(pinstances.get(i).getAlgorithmID()+"$"+pinstances.get(i).getName()+"$"+pinstances.get(i).getDescription());
	
			
			response = new AllPIResponse(pinstances, 200);
		} catch (Exception e) {
			response = new AllPIResponse(400, "Unable to get Problem Instance(" + e.getMessage() + ")");
		}
		return response;
	}

	List<PI> getPI(String algoID) throws Exception {
		if (logger != null) {
			logger.log("in get Problem Instance");
		}
		PIDAO dao = new PIDAO();
		System.out.println("connected to DB");

		return dao.getPI(algoID);
	}
}
