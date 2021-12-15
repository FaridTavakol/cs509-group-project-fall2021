package edu.wpi.cs.proteus.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.proteus.db.AlgorithmsDAO;
import edu.wpi.cs.proteus.http.GetAlgorithmByIdRequest;
import edu.wpi.cs.proteus.http.GetAlgorithmByIdResponse;
import edu.wpi.cs.proteus.model.Algorithm;

public class GetAlgorithmByIdHandler implements RequestHandler<GetAlgorithmByIdRequest, GetAlgorithmByIdResponse> {

	LambdaLogger logger;

	@Override
	public GetAlgorithmByIdResponse handleRequest(GetAlgorithmByIdRequest input, Context context)
	{
		GetAlgorithmByIdResponse response;
		try
		{
			AlgorithmsDAO dao = new AlgorithmsDAO();
			System.out.println("connected to DB");
			Algorithm algo = new Algorithm();
			algo = dao.getAlgorithmByID(input.getID());
			System.out.println(algo.getAlgorithmName());

			if (getAlgorithmById(input.getID(), dao))
			{
				Algorithm algorithm = new Algorithm();
				algorithm = dao.getAlgorithmByID(input.getID());
				response = new GetAlgorithmByIdResponse(algorithm.getAlgorithmName(), algorithm.getAlgorithmId(),
						algorithm.getClassificationId());

			} else
			{
				response = new GetAlgorithmByIdResponse();
			}
		} catch (Exception e)
		{
			response = new GetAlgorithmByIdResponse("Unable to Fetch Algorithm: " + "(" + e.getMessage() + ")");
		}
		return response;
	}

	boolean getAlgorithmById(String AlgorithmId_, AlgorithmsDAO dao) throws Exception
	{
		if (logger != null)
		{
			logger.log("in Get Algorithm By Id");
		}

		// check if present
		Algorithm exist = dao.getAlgorithmByID(AlgorithmId_);

		if (exist != null)
		{
			System.out.println("Handler-> Algorithm was found!");
			return true;
		} else
		{
			System.out.println("Handler-> Algorithm was NOT found!");
			return false;
		}
	}

}