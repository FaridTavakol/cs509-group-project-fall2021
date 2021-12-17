package edu.wpi.cs.proteus.demo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

public class AddImplementationLambdaFunctionHandlerTest {
	
    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("compute");

        return ctx;
    }
    
    @Test
    public void testAddImplementationLambdaFunctionHandler() throws IOException {
    	AddImplementationLambdaFunctionHandler handler = new AddImplementationLambdaFunctionHandler();
    	String SAMPLE_INPUT_STRING = "{\"algorithmID\":\"49b57e24-d5ae-4b7e-96bb-b3796f32a480\",\"language\":\"HTML\",\"details\":\"test details\",\"url\":\"ViewUser.html\",\"requestedBy\":\"A\"}";
    	
        Context ctx = createContext();
        InputStream is = new ByteArrayInputStream(SAMPLE_INPUT_STRING.getBytes());
        OutputStream os = new ByteArrayOutputStream();
        
		handler.handleRequest(is, os, ctx);
    }
    
    @Test
    public void testAddImplementationLambdaFunctionHandlerInvalidID() throws IOException {
    	AddImplementationLambdaFunctionHandler handler = new AddImplementationLambdaFunctionHandler();
    	String SAMPLE_INPUT_STRING = "{\"algorithmID\":\"invalidString\",\"requestedBy\":\"A\"}";
    	
        Context ctx = createContext();
        InputStream is = new ByteArrayInputStream(SAMPLE_INPUT_STRING.getBytes());
        OutputStream os = new ByteArrayOutputStream();
        
		handler.handleRequest(is, os, ctx);
    }
    
}



