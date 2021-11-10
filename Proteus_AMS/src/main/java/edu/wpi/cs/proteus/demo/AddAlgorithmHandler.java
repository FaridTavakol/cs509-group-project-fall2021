package edu.wpi.cs.proteus.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.proteus.db.AlgorithmsDAO;
import edu.wpi.cs.proteus.http.AddAlgorithmRequest;
import edu.wpi.cs.proteus.http.AddAlgorithmResponse;
import edu.wpi.cs.proteus.model.Algorithm;

public class AddAlgorithmHandler implements RequestHandler<AddAlgorithmRequest, AddAlgorithmResponse> {
	// Members
	LambdaLogger logger;

	@Override
	public AddAlgorithmResponse handleRequest(AddAlgorithmRequest input, Context context)
	{
		AddAlgorithmResponse response;
		try
		{
			if (addAlgorithm(input.getAlgorithmName()))
			{
				response = new AddAlgorithmResponse(input.getAlgorithmName());
			} else
			{
				response = new AddAlgorithmResponse(input.getAlgorithmName(), 400);
			}
		} catch (Exception e)
		{
			response = new AddAlgorithmResponse("Unable to add Algorithm: " + "(" + e.getMessage() + ")", 400);
		}
		return response;
	}

	boolean addAlgorithm(String name) throws Exception
	{
		if (logger != null)
		{
			logger.log("in addAlgorithm");
		}
		AlgorithmsDAO dao = new AlgorithmsDAO();

		// check if present
		Algorithm exist = dao.getAlgorithm(name);
		Algorithm algorithm = new Algorithm(name);
		if (exist == null)
		{
			return dao.addAlgorithm(algorithm);
		} else
		{
			return false;
		}
	}

}
