package edu.wpi.cs.proteus.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.proteus.db.AlgorithmsDAO;
import edu.wpi.cs.proteus.http.DeleteAlgorithmRequest;
import edu.wpi.cs.proteus.http.Response;

public class DeleteAlgorithmHandler implements RequestHandler<DeleteAlgorithmRequest, Response> {

	LambdaLogger logger;

	@Override
	public Response handleRequest(DeleteAlgorithmRequest input, Context context)
	{
		context.getLogger().log("Input: " + input);
		Response response;

		try
		{

			if (deleteAlgorithm(input.getAlgorithmID()))
			{
				response = new Response(200, "Algorithm Deleted Successfully");
			} else
			{
				response = new Response(422, "Error Occurred");
			}
		} catch (Exception e)
		{
			response = new Response(400, e.getMessage());
		}

		return response;
	}

	boolean deleteAlgorithm(String ID) throws Exception
	{
		if (logger != null)
		{
			logger.log("in Delete Algorithm Instance");
		}

		AlgorithmsDAO dao = new AlgorithmsDAO();
		System.out.println("connected to DB");
		// Gets The method takes the ID of the algorithm and deletes an algorithm
		// matching with that ID.
		boolean result = dao.deleteAlgorithm(ID);

		if (result)
		{
			return true;
		} else
		{
			logger.log("NOT successfull in deleting the algorithm!");
			return result;
		}

	}
}
