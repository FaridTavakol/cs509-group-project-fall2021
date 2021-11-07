package edu.wpi.cs.proteus.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.proteus.db.UsersDAO;
import edu.wpi.cs.proteus.http.LoginRequest;
import edu.wpi.cs.proteus.http.Response;
import edu.wpi.cs.proteus.model.User;

public class LoginLambdaFunctionHandler implements RequestHandler<LoginRequest, Response> {

    LambdaLogger logger;
    @Override
    public Response handleRequest(LoginRequest input, Context context) {

    	context.getLogger().log("Input: " + input);
        logger = context.getLogger();
		logger.log("Loading Java Lambda handler of Login Handler");
		logger.log(input.toString());
		

		boolean fail = false;
		String failMessage = "";
		String email = "";
		Response response=null;
		
		try {
			User u = loadUserFromRDSIFExists(input.getEmail(),input.getPassword());
			if(u!=null)
			{
				response = new Response(200,u.getRole());
				
			}
			else
			{
				response = new Response(400, "No Account Exists.");
				
			}
			
		} catch (Exception e) {
			
			failMessage = e.getMessage();
			response = new Response(400, failMessage);
		}


		return response; 
    }
    
    
	User loadUserFromRDSIFExists(String email,String password) throws Exception {
		if (logger != null) {
			logger.log("in loadValue");
		}
		UsersDAO dao = new UsersDAO();
		if (logger != null) {
			logger.log("retrieved DAO");}
		User user = dao.getUserCredentials(email,password);
		if (logger != null) {
			logger.log("retrieved User");
		}
		return user;
	}

}
