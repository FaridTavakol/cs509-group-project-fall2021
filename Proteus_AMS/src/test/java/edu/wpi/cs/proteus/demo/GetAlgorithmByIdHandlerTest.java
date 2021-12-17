package edu.wpi.cs.proteus.demo;

import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import edu.wpi.cs.proteus.db.AlgorithmsDAO;
import edu.wpi.cs.proteus.http.GetAlgorithmByIdRequest;
import edu.wpi.cs.proteus.http.GetAlgorithmByIdResponse;
import edu.wpi.cs.proteus.model.Algorithm;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class GetAlgorithmByIdHandlerTest {

	public GetAlgorithmByIdRequest input = new GetAlgorithmByIdRequest("4");

//	@BeforeClass
//	public static void createInput() throws IOException
//	{
//		// TODO: set up your sample input object here.
//		input = null;
//	}

	private Context createContext()
	{
		TestContext ctx = new TestContext();

		// TODO: customize your context here if needed.
		ctx.setFunctionName("Your Function Name");

		return ctx;
	}

	@Test
	public void testGetAlgorithmByIdHandler() throws Exception
	{
		AlgorithmsDAO dao = new AlgorithmsDAO();
		Algorithm algo = new Algorithm("203", "1", "4");
		dao.addAlgorithm(algo);

		GetAlgorithmByIdHandler handler = new GetAlgorithmByIdHandler();
		Context ctx = createContext();
//		input.setID("1");
		GetAlgorithmByIdResponse output = handler.handleRequest(input, ctx);

		// TODO: validate output here if needed.
		String ID = output.algorithmId;

	}
}