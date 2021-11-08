package edu.wpi.cs.proteus.demo;

import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.wpi.cs.proteus.db.AlgorithmsDAO;
import edu.wpi.cs.proteus.db.ImplementationsDAO;
import edu.wpi.cs.proteus.http.AddImplementationRequest;
import edu.wpi.cs.proteus.http.Response;
import edu.wpi.cs.proteus.model.Algorithm;
import edu.wpi.cs.proteus.model.Benchmark;
import edu.wpi.cs.proteus.model.Implementation;

public class AddImplementationLambdaFunctionHandler implements RequestHandler<AddImplementationRequest, Response>{

    LambdaLogger logger;
    @Override
    public Response handleRequest(AddImplementationRequest input, Context context) {

		logger = context.getLogger();
    	logger.log("Input: " + input.toString());
		logger.log("Loading Java Lambda handler of AddImplementationRequestHandler");
		
		Gson gson = new GsonBuilder().create();
		AddImplementationRequest addImplementationRequest = null;

		addImplementationRequest = gson.fromJson(input.toString(), AddImplementationRequest.class);

		Response response=null;
		
		try {
			Algorithm algorithm = new AlgorithmsDAO().getAlgorithm(addImplementationRequest.getAlgorithmName());
			Implementation newImplementation = new Implementation("TODO", "TODO", addImplementationRequest.getDetails(), addImplementationRequest.getLanguage(), algorithm, new ArrayList<Benchmark>());
			ImplementationsDAO implementationsDAO = new ImplementationsDAO();
			boolean result = implementationsDAO.addImplementation(newImplementation);
			if(result) {
				response = new Response(200, "Implementation added succesfully!");	
			}	
		} catch (Exception e) {
			String failMessage = "Implementation failed to be added. " + e.getMessage();
			response = new Response(400, failMessage);
		}

		return response; 
    }

}
