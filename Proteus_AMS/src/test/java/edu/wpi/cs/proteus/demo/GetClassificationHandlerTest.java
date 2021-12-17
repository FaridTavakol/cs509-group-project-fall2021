package edu.wpi.cs.proteus.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import edu.wpi.cs.proteus.http.GetClassificationByIdRequest;
import edu.wpi.cs.proteus.http.GetClassificationByIdResponse;
import edu.wpi.cs.proteus.http.GetClassificationRequest;
import edu.wpi.cs.proteus.http.GetClassificationResponse;

public class GetClassificationHandlerTest {

	private static GetClassificationRequest input;

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
	public void testGetClassificationHandler() {
		GetClassificationHandler handler = new GetClassificationHandler();
		Context ctx = createContext();

		input = new GetClassificationRequest();
		input.setClassificationName("test1");
		input.getClassificationName();
		input.toString();
		GetClassificationResponse output = handler.handleRequest(input, ctx);

		// TODO: validate output here if needed.
		
		output.toString();

		output = new GetClassificationResponse(200, "A");
		GetClassificationRequest a= new GetClassificationRequest();
		a=new GetClassificationRequest("a");
	}
}
