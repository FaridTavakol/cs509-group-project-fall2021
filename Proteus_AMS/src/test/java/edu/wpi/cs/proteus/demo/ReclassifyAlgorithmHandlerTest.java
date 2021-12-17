package edu.wpi.cs.proteus.demo;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import edu.wpi.cs.proteus.db.AlgorithmsDAO;
import edu.wpi.cs.proteus.http.ReclassifyAlgorithmRequest;
import edu.wpi.cs.proteus.http.Response;
import edu.wpi.cs.proteus.model.Algorithm;

public class ReclassifyAlgorithmHandlerTest {

	private static ReclassifyAlgorithmRequest input = new ReclassifyAlgorithmRequest("123s", "23423");

	@BeforeClass
	public static void createInput() throws IOException
	{
		// TODO: set up your sample input object here.
		input = null;
	}

	private Context createContext()
	{
		TestContext ctx = new TestContext();

		// TODO: customize your context here if needed.
		ctx.setFunctionName("Your Function Name");

		return ctx;
	}

	@Test
	public void testReclassifyAlgorithmHandler() throws Exception
	{
		ReclassifyAlgorithmHandler handler = new ReclassifyAlgorithmHandler();
		Context ctx = createContext();
		String algorithmName = "Tsdflgorithm";
		String classificationId = "45";
		String algorithmId = "123s";
		Algorithm algo = new Algorithm(algorithmName, classificationId, algorithmId);
		AlgorithmsDAO dao = new AlgorithmsDAO();
		dao.addAlgorithm(algo);
		Response rs = handler.handleRequest(input, ctx);
		String ww = rs.Message;
		int w = rs.statusCode;

	}
}
