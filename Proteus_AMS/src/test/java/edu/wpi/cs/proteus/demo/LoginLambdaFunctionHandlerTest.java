package edu.wpi.cs.proteus.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;

import edu.wpi.cs.proteus.http.LoginRequest;
import edu.wpi.cs.proteus.http.Response;
import edu.wpi.cs.proteus.model.User;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class LoginLambdaFunctionHandlerTest {

    private static LoginRequest input;

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
    public void testLoginLambdaFunctionHandler() throws IOException {
        LoginLambdaFunctionHandler handler = new LoginLambdaFunctionHandler();
        Context ctx = createContext();
        
        User u= new User();
        u.setemail("A");
        u.setName("Atifa");
        u.setPassword("1234");
        u.setRole("Registered");
        String SAMPLE_INPUT_STRING = "{\"email\": \"atifa_sarwar@hotmail.com\", \"password\": \"1234\"}";
        
        LoginRequest req = new LoginRequest("abc@test.com","123");
		Response response = handler.handleRequest(req, ctx);
		Assert.assertEquals(200, response.statusCode);
		
		
        req=new LoginRequest();
        req.setEmail("a@hotmail.com");
        req.setEmail("1234");
        response = handler.handleRequest(req, ctx);
		Assert.assertEquals(400, response.statusCode);
		
		req=new LoginRequest();
		req.setPassword("A");
    
    }
}
