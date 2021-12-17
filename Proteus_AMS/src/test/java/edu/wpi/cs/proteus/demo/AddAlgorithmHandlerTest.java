package edu.wpi.cs.proteus.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import edu.wpi.cs.proteus.db.AlgorithmsDAO;
import edu.wpi.cs.proteus.http.AddAlgorithmRequest;
import edu.wpi.cs.proteus.http.AddAlgorithmResponse;
import edu.wpi.cs.proteus.model.Algorithm;

public class AddAlgorithmHandlerTest {

	private static AddAlgorithmRequest input;

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
	public void testAddAlgorithmHandler() throws Exception
	{
		AddAlgorithmHandler handler = new AddAlgorithmHandler();
		Context ctx = createContext();
		String algorithmName = "TestAlgorithm";
		String classificationId = "4";
		String algorithmId = "123456";
		AddAlgorithmRequest req = new AddAlgorithmRequest(algorithmId, classificationId, algorithmId);
		AddAlgorithmResponse response = handler.handleRequest(req, ctx);

		// TODO: validate output here if needed.
		Assert.assertNotEquals(200, response.statusCode);
		Assert.assertNotEquals("success", response.Message);

		Algorithm algo = new Algorithm(algorithmName, classificationId, algorithmId);
		Assert.assertEquals(algorithmName, algo.getAlgorithmName());
		Assert.assertEquals(classificationId, algo.getClassificationId());
		Assert.assertEquals(algorithmId, algo.getAlgorithmId());
		algo.setAlgorithmId("1");
		algo.setAlgorithmName("2");
		algo.setClassificationId("3");
		AlgorithmsDAO dao = new AlgorithmsDAO();
		dao.addAlgorithm(algo);
		dao.getAllAlgorithms();
		dao.reclassifyAlgorithm("1", "5");
		dao.deleteAlgorithm("1");
		dao.getAlgorithmByID("1");
	}
}
