package edu.wpi.cs.proteus.demo;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;

import edu.wpi.cs.proteus.db.LogDAO;
import edu.wpi.cs.proteus.db.UsersDAO;
import edu.wpi.cs.proteus.http.AllUserActivityResponse;
import edu.wpi.cs.proteus.http.AllUsersResponse;
import edu.wpi.cs.proteus.model.Log;
import edu.wpi.cs.proteus.model.User;

public class GetUserActivityHandler implements RequestHandler<Object, AllUserActivityResponse> {

	LambdaLogger logger;
    @Override
   
    public AllUserActivityResponse handleRequest(Object input, Context context) {
        context.getLogger().log("Input: " + input);
        
        Gson gson = new Gson();
		User instance = gson.fromJson(input.toString(), User.class);
		
    	AllUserActivityResponse response;

		try {
			List<Log> logs = getActivityLog(instance.getEmail());		
			response = new AllUserActivityResponse(logs, 200);
		} catch (Exception e) {
			response = new AllUserActivityResponse(400,  e.getMessage());
		}
		return response;
    }
    List<Log> getActivityLog(String email) throws Exception {
		if (logger != null) {
			logger.log("in Get User Activity Handler");
		}
		LogDAO dao = new LogDAO();
		System.out.println("connected to DB");

		return dao.getLog(email);
	}

}
