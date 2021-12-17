package edu.wpi.cs.proteus.demo;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import edu.wpi.cs.proteus.http.AllAlgorithmsResponse;
import edu.wpi.cs.proteus.model.Algorithm;

public class AllAlgorithmHandlerTest {

	private static Object input;

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
	public void testAllAlgorithmHandler() throws Exception
	{
		AllAlgorithmHandler handler = new AllAlgorithmHandler();
		Context ctx = createContext();

		Object req = new Object();
		AllAlgorithmsResponse response = handler.handleRequest(req, ctx);

		// TODO: validate output here if needed.
		Assert.assertEquals(200, response.statusCode);
		Assert.assertEquals("Successfully retrieved List of All Algorithms", response.error);
		List<Algorithm> list = response.list;

	}
}
