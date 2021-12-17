package edu.wpi.cs.proteus.demo;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import edu.wpi.cs.proteus.db.AlgorithmsDAO;
import edu.wpi.cs.proteus.http.DeleteAlgorithmRequest;
import edu.wpi.cs.proteus.http.Response;
import edu.wpi.cs.proteus.model.Algorithm;

public class DeleteAlgorithmHandlerTest {

	private static DeleteAlgorithmRequest input;

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
		ctx.setFunctionName("compute");

		return ctx;
	}

	@Test
	public void testDeleteAlgorithmHandler() throws Exception
	{
		AlgorithmsDAO dao = new AlgorithmsDAO();
		Algorithm algo = new Algorithm("1", "1", "1");
		dao.addAlgorithm(algo);
		DeleteAlgorithmHandler handler = new DeleteAlgorithmHandler();
		Context ctx = createContext();

		DeleteAlgorithmRequest req = new DeleteAlgorithmRequest("1");
		req.getAlgorithmID();
		Response response = handler.handleRequest(req, ctx);
		response = handler.handleRequest(req, ctx);
	}
}
