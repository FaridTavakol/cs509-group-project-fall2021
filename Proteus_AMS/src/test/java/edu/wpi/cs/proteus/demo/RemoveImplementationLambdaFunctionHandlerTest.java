package edu.wpi.cs.proteus.demo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import edu.wpi.cs.proteus.db.ImplementationsDAO;
import edu.wpi.cs.proteus.model.Implementation;

public class RemoveImplementationLambdaFunctionHandlerTest {
	
    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("compute");

        return ctx;
    }
    
    @Test
    public void testRemoveImplementationLambdaFunctionHandler() throws IOException {
    	RemoveImplementationLambdaFunctionHandler handler = new RemoveImplementationLambdaFunctionHandler();
    	
    	ImplementationsDAO implementationsDAO = new ImplementationsDAO();
    	String implementationID = "fed84195-b18c-4de7-aec2-e7333ca65152";
    	try {
			List<Implementation> implementations = implementationsDAO.getAllImplementations("49b57e24-d5ae-4b7e-96bb-b3796f32a480");
			implementationID = implementations.get(0).getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	String SAMPLE_INPUT_STRING = "{\"implementationID\":\"" + implementationID + "\",\"requestedBy\":\"A\"}";
    	
        Context ctx = createContext();
        InputStream is = new ByteArrayInputStream(SAMPLE_INPUT_STRING.getBytes());
        OutputStream os = new ByteArrayOutputStream();
        
		handler.handleRequest(is, os, ctx);
    }
    
    @Test
    public void testRemoveImplementationLambdaFunctionHandlerInvalid() throws IOException {
    	RemoveImplementationLambdaFunctionHandler handler = new RemoveImplementationLambdaFunctionHandler();
    	String SAMPLE_INPUT_STRING = "{\"implementationID\":\"invalidString\",\"requestedBy\":\"A\"}";
    	
        Context ctx = createContext();
        InputStream is = new ByteArrayInputStream(SAMPLE_INPUT_STRING.getBytes());
        OutputStream os = new ByteArrayOutputStream();
        
		handler.handleRequest(is, os, ctx);
    }
}



