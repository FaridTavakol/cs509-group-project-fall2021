package edu.wpi.cs.proteus.demo;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.proteus.db.AlgorithmsDAO;
import edu.wpi.cs.proteus.http.AllAlgorithmsResponse;
import edu.wpi.cs.proteus.model.Algorithm;

public class AllAlgorithmHandler implements RequestHandler<Object, AllAlgorithmsResponse> {

	// members
	public LambdaLogger logger;

	// Methods
	List<Algorithm> getAllAlgorithms() throws Exception
	{
		logger.log("in getAllAlgorithms");
		AlgorithmsDAO dao = new AlgorithmsDAO();

		return dao.getAllAlgorithms();
	}

	@Override
	public AllAlgorithmsResponse handleRequest(Object input, Context context)
	{
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list all Algorithms");

		AllAlgorithmsResponse response;
		try
		{
			// get all user defined constants AND system-defined constants.
			// Note that user defined constants override system-defined constants.
			List<Algorithm> list = getAllAlgorithms();

			response = new AllAlgorithmsResponse(list, 200);
		} catch (Exception e)
		{
			response = new AllAlgorithmsResponse(400, e.getMessage());
		}

		return response;
	}

}
