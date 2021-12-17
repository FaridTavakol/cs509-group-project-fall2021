package edu.wpi.cs.proteus.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import edu.wpi.cs.proteus.http.AllClassificationsResponse;
import edu.wpi.cs.proteus.http.ClassificationHierarchyResponse;

public class ClassificationHierarchyHandlerTest {
	
	private static Object input;
	@BeforeClass
	public static void createInput() throws IOException {
		// TODO: set up your sample input object here.
		input = null;
	}

	private Context createContext() {
		TestContext ctx = new TestContext();

		// TODO: customize your context here if needed.
		ctx.setFunctionName("compute");

		return ctx;
	}

	@Test
	public void testClassificationHierarchyHandler() {
		ClassificationHierarchyHandler handler = new ClassificationHierarchyHandler();
		Context ctx = createContext();

		
		ClassificationHierarchyResponse output = handler.handleRequest(input, ctx);

		// TODO: validate output here if needed.
		Assert.assertEquals(200, output.httpCode);
		
		
		output = new ClassificationHierarchyResponse(400, "");
		

	}
}
