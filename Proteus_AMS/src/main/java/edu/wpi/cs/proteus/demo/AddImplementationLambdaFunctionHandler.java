package edu.wpi.cs.proteus.demo;

import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.proteus.db.ImplementationsDAO;
import edu.wpi.cs.proteus.http.AddImplementationRequest;
import edu.wpi.cs.proteus.http.Response;
import edu.wpi.cs.proteus.model.Implementation;

public class AddImplementationLambdaFunctionHandler implements RequestHandler<AddImplementationRequest, Response>{

    LambdaLogger logger;
    @Override
    public Response handleRequest(AddImplementationRequest input, Context context) {
		logger = context.getLogger();
    	logger.log("Input: " + input.toString());
		logger.log("Loading Java Lambda handler of AddImplementationRequestHandler");
	
		Response response=null;
		try {
			ImplementationsDAO implementationsDAO = new ImplementationsDAO();
			String algorithmID = input.getAlgorithmID();
			String language = input.getLanguage();
			String url = input.getUrl();
			String details = input.getDetails();
			
			Implementation newImplementation = new Implementation("N/A", url, details, language, algorithmID, new ArrayList<String>());
			
			boolean result = implementationsDAO.addImplementation(newImplementation);
			
			if(result) {
				response = new Response(200, "Implementation added succesfully!");	
			}	
		} catch (Exception e) {
			String failMessage = "Input: " + input.toString() + ". " + e.getMessage();
			response = new Response(400, failMessage);
		}

		return response; 
    }

}
