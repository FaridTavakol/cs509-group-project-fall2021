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

			if (addAlgorithm(input.getAlgorithmName(), input.getClassificationId()))
			{
				response = new AddAlgorithmResponse(input.getAlgorithmName());

			} else
			{
				response = new AddAlgorithmResponse(input.getAlgorithmName(), 422);
			}
		} catch (Exception e)
		{
			response = new AddAlgorithmResponse("Unable to add Algorithm: " + "(" + e.getMessage() + ")", 400);
		}
		return response;
	}
//
//	boolean addAlgorithm(String AlgorithmName_, String classificationId_, String AlgorithmId_) throws Exception
//	{
//		if (logger != null)
//		{
//			logger.log("in addAlgorithm");
//		}
//		AlgorithmsDAO dao = new AlgorithmsDAO();
//		System.out.println("connected to DB");
//
//		// check if present
//		Algorithm exist = dao.getAlgorithm(AlgorithmName_);
//		Algorithm algorithm = new Algorithm(AlgorithmName_, classificationId_, AlgorithmId_);
//		if (exist == null)
//		{
//			System.out.println("handler - add classification");
//			return dao.addAlgorithm(algorithm);
//		} else
//		{
//			return false;
//		}
//	}

	boolean addAlgorithm(String AlgorithmName_, String classificationId_) throws Exception
	{
		if (logger != null)
		{
			logger.log("in addAlgorithm");
		}
		AlgorithmsDAO dao = new AlgorithmsDAO();
		System.out.println("connected to DB");

		// check if present
		Algorithm exist = dao.getAlgorithmByName(AlgorithmName_);
		Algorithm algorithm = new Algorithm(AlgorithmName_, classificationId_);
		if (exist == null)
		{
			System.out.println("handler - add Algorithm");
			return dao.addAlgorithm(algorithm);
		} else
		{
			return false;
		}
	}

}
