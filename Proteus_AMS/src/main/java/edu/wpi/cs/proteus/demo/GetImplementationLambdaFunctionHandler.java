package edu.wpi.cs.proteus.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.wpi.cs.proteus.db.ImplementationsDAO;
import edu.wpi.cs.proteus.http.AddImplementationRequest;
import edu.wpi.cs.proteus.http.GetImplementationRequest;
import edu.wpi.cs.proteus.http.Response;
import edu.wpi.cs.proteus.model.Implementation;

public class GetImplementationLambdaFunctionHandler implements RequestHandler<GetImplementationRequest, Response>{

    LambdaLogger logger;
    @Override
    public Response handleRequest(GetImplementationRequest input, Context context) {

		logger = context.getLogger();
    	logger.log("Input: " + input.toString());
		logger.log("Loading Java Lambda handler of AddImplementationRequestHandler");

		Response response=null;
		
//		Gson gson = new GsonBuilder().create();
//		AddImplementationRequest addImplementationRequest = null;
//
//		addImplementationRequest = gson.fromJson(input.toString(), AddImplementationRequest.class);
		
		try {
			ImplementationsDAO implementationsDAO = new ImplementationsDAO();
			Implementation implementation = implementationsDAO.getImplementation(input.getImplementationID());

			response = new Response(200, implementation.toString());	
		} catch (Exception e) {
			String failMessage = " Failed to get implementation. Request: " + input.toString() +  ". Message: " + e.getMessage();
			response = new Response(400, failMessage);
		}

		return response; 
    }

}
