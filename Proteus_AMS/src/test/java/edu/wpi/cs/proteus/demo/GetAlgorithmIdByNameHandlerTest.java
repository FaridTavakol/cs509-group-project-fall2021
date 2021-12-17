
package edu.wpi.cs.proteus.demo;

import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import edu.wpi.cs.proteus.db.AlgorithmsDAO;
import edu.wpi.cs.proteus.http.GetAlgorithmIdByNameRequest;
import edu.wpi.cs.proteus.http.GetAlgorithmIdByNameResponse;
import edu.wpi.cs.proteus.model.Algorithm;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class GetAlgorithmIdByNameHandlerTest {

	public GetAlgorithmIdByNameRequest input = new GetAlgorithmIdByNameRequest("33");

	private Context createContext()
	{
		TestContext ctx = new TestContext();

		// TODO: customize your context here if needed.
		ctx.setFunctionName("Your Function Name");

		return ctx;
	}

	@Test
	public void testGetAlgorithmIdByNameHandler() throws Exception
	{
		AlgorithmsDAO dao = new AlgorithmsDAO();
		Algorithm algo = new Algorithm("33", "1", "55");
		dao.addAlgorithm(algo);

		GetAlgorithmIdByNameHandler handler = new GetAlgorithmIdByNameHandler();
		Context ctx = createContext();
//			input.setID("1");
		GetAlgorithmIdByNameResponse output = handler.handleRequest(input, ctx);

		// TODO: validate output here if needed.
		String ID = output.algorithmId;
		input.getAlgorithmName();

	}
}