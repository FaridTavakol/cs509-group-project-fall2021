package edu.wpi.cs.proteus.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.proteus.db.AlgorithmsDAO;
import edu.wpi.cs.proteus.http.ReclassifyAlgorithmRequest;
import edu.wpi.cs.proteus.http.Response;

public class ReclassifyAlgorithmHandler implements RequestHandler<ReclassifyAlgorithmRequest, Response> {
	LambdaLogger logger;

	public Response handleRequest(final ReclassifyAlgorithmRequest input, final Context context)
	{
		(this.logger = context.getLogger()).log("Input: " + input.toString());
		this.logger.log("Loading Java Lambda handler of GetAlgorithmIdByNameHandler");
		Response response = null;
		try
		{
			final boolean result = this.reclassifyAlgorithm(input.getAlgorithmId(), input.getClassificationId());
			if (result)
			{
				response = new Response(200, "success!");
			} else
			{
				response = new Response(422, "failed!");
			}
		} catch (Exception e)
		{
			final String failMessage = "Failed to change algorithm ID. " + e.getMessage();
			response = new Response(400, failMessage);
		}
		return response;
	}

	public boolean reclassifyAlgorithm(final String algorithmId, final String classificationId) throws Exception
	{
		boolean result = false;
		if (this.logger != null)
		{
			this.logger.log("in Delete Algorithm Instance");
		}
		AlgorithmsDAO dao = new AlgorithmsDAO();
		System.out.println("connected to DB");
		result = dao.reclassifyAlgorithm(algorithmId, classificationId);
		return result;
	}
}