package edu.wpi.cs.proteus.demo;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;

import edu.wpi.cs.proteus.db.UsersDAO;
import edu.wpi.cs.proteus.http.AllPIResponse;
import edu.wpi.cs.proteus.http.AllUsersResponse;
import edu.wpi.cs.proteus.model.User;

public class GetAllUserHandler implements RequestHandler<Object, AllUsersResponse> {

	LambdaLogger logger;
    @Override
    public AllUsersResponse handleRequest(Object input, Context context) {
        context.getLogger().log("Input: " + input);

    	AllUsersResponse response;

		try {
			List<User> users = getAllUser();		
			response = new AllUsersResponse(users, 200);
		} catch (Exception e) {
			response = new AllUsersResponse(400,  e.getMessage());
		}
		return response;
    }
    
    List<User> getAllUser() throws Exception {
		if (logger != null) {
			logger.log("in Get User");
		}
		UsersDAO dao = new UsersDAO();
		System.out.println("connected to DB");

		return dao.getAllUser();
	}

}
