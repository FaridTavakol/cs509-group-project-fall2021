package edu.wpi.cs.proteus.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import edu.wpi.cs.proteus.http.ClassificationHierarchyResponse;
import edu.wpi.cs.proteus.http.GetClassificationByIdRequest;
import edu.wpi.cs.proteus.http.GetClassificationByIdResponse;

public class GetClassificationByIDHandlerTest {

	private static GetClassificationByIdRequest input;

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
	public void testGetClassificationByIDHandler() {
		GetClassificationByIDHandler handler = new GetClassificationByIDHandler();
		Context ctx = createContext();

		input = new GetClassificationByIdRequest("2");
		GetClassificationByIdResponse output = handler.handleRequest(input, ctx);

		// TODO: validate output here if needed.
		input.getClassificationId();
		input.toString();

		output = new GetClassificationByIdResponse("200", "A");
		input = new GetClassificationByIdRequest();
		input.toString();
	}
}
