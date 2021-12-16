package edu.wpi.cs.proteus.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;

import edu.wpi.cs.proteus.db.AlgorithmsDAO;
import edu.wpi.cs.proteus.db.LogDAO;
import edu.wpi.cs.proteus.http.GetObjectsByID;
import edu.wpi.cs.proteus.http.Response;
import edu.wpi.cs.proteus.model.Log;

public class DeleteAlgorithmHandler implements RequestHandler<Object, Response> {

	LambdaLogger logger;

	@Override
	public Response handleRequest(Object input, Context context)
	{
		context.getLogger().log("Input: " + input);
		Response response;

		Gson gson = new Gson();
		GetObjectsByID instance = gson.fromJson(input.toString(), GetObjectsByID.class);

		try
		{

			if (deleteAlgorithm(instance.getID(), instance.getRequestedBy()))
			{
				response = new Response(200, "Algorithm Deleted Successfully");
			} else
			{
				response = new Response(400, "Error Occurred");
			}
		} catch (Exception e)
		{
			response = new Response(400, e.getMessage());
		}

		return response;
	}

	boolean deleteAlgorithm(String ID, String requestedBy) throws Exception
	{
		if (logger != null)
		{
			logger.log("in Delete Algorithm Instance");
		}

		AlgorithmsDAO dao = new AlgorithmsDAO();
		System.out.println("connected to DB");
		// Gets The method takes the ID of the algorithm and deletes an algorithm
		// matching with that ID.
		boolean result = dao.deleteAlgorithm(dao.getAlgorithmByID(ID));

		if (result)
		{
			Log entry = new Log(requestedBy, "Deleted Algorithm", java.time.LocalDate.now().toString());
			LogDAO ldao = new LogDAO();
			return ldao.addLogEntry(entry);
		}
		return result;

	}

}
