package edu.wpi.cs.proteus.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.proteus.db.UsersDAO;
import edu.wpi.cs.proteus.http.RegisterRequest;
import edu.wpi.cs.proteus.http.RegisterResponse;
import edu.wpi.cs.proteus.model.User;


public class RegisterLambdaFunctionHandler implements RequestHandler<RegisterRequest, RegisterResponse>{

    LambdaLogger logger;
    @Override
    public RegisterResponse handleRequest(RegisterRequest input, Context context) {

    	context.getLogger().log("Input: " + input);
        logger = context.getLogger();
		logger.log("Loading Java Lambda handler of RequestHandler");
		logger.log(input.toString());
		

		boolean fail = false;
		String failMessage = "";
		String email = "";
		RegisterResponse response=null;
		
		try {
			User u = loadUserFromRDSIFExists(input.getEmail());
			if(u!=null)
			{
				failMessage="An account already exist with the Email";
				response = new RegisterResponse(400, failMessage);
				
			}
			else
			{
				User newUser = new User(input.getName(),input.getEmail(),input.getPassword(),"Register");
				UsersDAO dao = new UsersDAO();
				boolean result = dao.addUser(newUser);
				if(result==true)
				{
					response = new RegisterResponse(200, newUser.getRole());
					
				}
				
			}
			
		} catch (Exception e) {
			
			failMessage = e.getMessage();// "No account with Email "+input.getEmail() + " Exists";
			response = new RegisterResponse(400, failMessage);
		}


		return response; 
    }
    


	User loadUserFromRDSIFExists(String email) throws Exception {
		if (logger != null) {
			logger.log("in loadValue");
		}
		UsersDAO dao = new UsersDAO();
		if (logger != null) {
			logger.log("retrieved DAO");}
		User user = dao.getUser(email);
		if (logger != null) {
			logger.log("retrieved User");
		}
		return user;
	}

}
