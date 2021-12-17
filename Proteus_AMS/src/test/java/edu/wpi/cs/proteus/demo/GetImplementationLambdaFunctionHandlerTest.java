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

public class GetImplementationLambdaFunctionHandlerTest {
	
    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("compute");

        return ctx;
    }
    
    @Test
    public void testGetImplementationLambdaFunctionHandler() throws IOException {
    	GetImplementationLambdaFunctionHandler handler = new GetImplementationLambdaFunctionHandler();
    	String SAMPLE_INPUT_STRING = "{\"implementationID\":\"1b2702c1-a227-4664-8393-b0d7d31f3be3\",\"requestedBy\":\"A\"}";
    	
        Context ctx = createContext();
        InputStream is = new ByteArrayInputStream(SAMPLE_INPUT_STRING.getBytes());
        OutputStream os = new ByteArrayOutputStream();
        
		handler.handleRequest(is, os, ctx);
    }
    
    @Test
    public void testGetImplementationLambdaFunctionHandlerInvalid() throws IOException {
    	GetImplementationLambdaFunctionHandler handler = new GetImplementationLambdaFunctionHandler();
    	String SAMPLE_INPUT_STRING = "{\"implementationID\":\"invalidString\",\"requestedBy\":\"A\"}";
    	
        Context ctx = createContext();
        InputStream is = new ByteArrayInputStream(SAMPLE_INPUT_STRING.getBytes());
        OutputStream os = new ByteArrayOutputStream();
        
		handler.handleRequest(is, os, ctx);
    }
}



