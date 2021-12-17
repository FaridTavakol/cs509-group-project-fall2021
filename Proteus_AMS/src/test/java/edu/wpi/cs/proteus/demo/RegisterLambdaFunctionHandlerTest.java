package edu.wpi.cs.proteus.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;

import edu.wpi.cs.proteus.http.RegisterRequest;
import edu.wpi.cs.proteus.http.RegisterResponse;

public class RegisterLambdaFunctionHandlerTest {

	private static RegisterRequest input;

	private Context createContext() {
		TestContext ctx = new TestContext();

		// TODO: customize your context here if needed.
		ctx.setFunctionName("compute");

		return ctx;
	}

	@Test
	public void testRegisterLambdaFunctionHandler() throws IOException {
		RegisterLambdaFunctionHandler handler = new RegisterLambdaFunctionHandler();
		Context ctx = createContext();

		String SAMPLE_INPUT_STRING = "{\"name\":\"Sample Testing\",\"email\": \"sampler@hotmail.com\", \"password\": \"1234\"}";

		RegisterRequest req = new Gson().fromJson(SAMPLE_INPUT_STRING, RegisterRequest.class);
		RegisterResponse response = handler.handleRequest(req, ctx);
		Assert.assertEquals(400, response.statusCode);

		req = new RegisterRequest("Sample Testing","sampler@hotmail.com","1234");
		response = handler.handleRequest(req, ctx);
		Assert.assertEquals(400, response.statusCode);
		
		req = new RegisterRequest();
		req.setEmail("abc@test.com");
		req.setName("abc");
		req.setPassword("123");
		response = handler.handleRequest(req, ctx);
		Assert.assertEquals(200, response.statusCode);
		

	}
}
