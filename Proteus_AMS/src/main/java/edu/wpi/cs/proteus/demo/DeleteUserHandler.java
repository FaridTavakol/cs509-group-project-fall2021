package edu.wpi.cs.proteus.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;

import edu.wpi.cs.proteus.db.UsersDAO;
import edu.wpi.cs.proteus.http.Response;
import edu.wpi.cs.proteus.model.User;

public class DeleteUserHandler implements RequestHandler<Object, Response> {

	LambdaLogger logger;

	@Override
	public Response handleRequest(Object input, Context context) {
		context.getLogger().log("Input: " + input);
		Response response;

		Gson gson = new Gson();
		User instance = gson.fromJson(input.toString(), User.class);

		try {

			if (deleteUser(instance.getEmail())) {
				response = new Response(200, "User Deleted Successfully");
			} else {
				response = new Response(400, "Error Occurred");
			}
		} catch (Exception e) {
			response = new Response(400, e.getMessage());
		}

		return response;
	}

	boolean deleteUser(String email) throws Exception {
		if (logger != null) {
			logger.log("in Delete User");
		}
		UsersDAO dao = new UsersDAO();
		System.out.println("connected to DB");

		return dao.deleteUser(email);

	}

}
