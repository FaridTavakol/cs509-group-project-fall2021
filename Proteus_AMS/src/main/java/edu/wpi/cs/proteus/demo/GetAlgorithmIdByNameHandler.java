package edu.wpi.cs.proteus.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.proteus.db.AlgorithmsDAO;
import edu.wpi.cs.proteus.http.GetAlgorithmIdByNameRequest;
import edu.wpi.cs.proteus.http.GetAlgorithmIdByNameResponse;
import edu.wpi.cs.proteus.model.Algorithm;

public class GetAlgorithmIdByNameHandler
		implements RequestHandler<GetAlgorithmIdByNameRequest, GetAlgorithmIdByNameResponse> {
	LambdaLogger logger;

	@Override
	public GetAlgorithmIdByNameResponse handleRequest(GetAlgorithmIdByNameRequest input, Context context)
	{
		logger = context.getLogger();
		logger.log("Input: " + input.toString());
		logger.log("Loading Java Lambda handler of GetAlgorithmIdByNameHandler");
		GetAlgorithmIdByNameResponse response = null;
		try
		{
			String AlgorithmId = GetAlgorithmIDByName(input.getAlgorithmName());
			if (AlgorithmId != null)
			{
				response = new GetAlgorithmIdByNameResponse(AlgorithmId);

			} else
			{
				response = new GetAlgorithmIdByNameResponse("Failed to get Algorithm ID", "422");

			}

		} catch (Exception e)
		{
			String failMessage = "Failed to get algorithm ID. " + e.getMessage();
			response = new GetAlgorithmIdByNameResponse(failMessage, "400");
		}

		return response;
	}

	public String GetAlgorithmIDByName(String name) throws Exception
	{
		if (logger != null)
		{
			logger.log("in Delete Algorithm Instance");
		}

		AlgorithmsDAO dao = new AlgorithmsDAO();
		System.out.println("connected to DB");
		Algorithm algo = null;
		algo = dao.getAlgorithm(name);
		if (algo != null)
		{
			return algo.getAlgorithmId();
		} else
		{
			return null;
		}

	}

}
