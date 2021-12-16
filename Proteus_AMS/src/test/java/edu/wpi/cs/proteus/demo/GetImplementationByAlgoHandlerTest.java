package edu.wpi.cs.proteus.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import edu.wpi.cs.proteus.http.AllBenchmarkResponse;
import edu.wpi.cs.proteus.http.AllImplementationsResponse;

public class GetImplementationByAlgoHandlerTest {
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
    public void testGetImplementationByAlgoHandler() {
    	GetImplementationByAlgoHandler handler = new GetImplementationByAlgoHandler();
        Context ctx = createContext();

        String SAMPLE_INPUT_STRING = "{\"ID\":\"2497a8f7-0c3e-4972-9983-d43052255e0e\",\"requestedBy\":\"A\"}";
        AllImplementationsResponse output = handler.handleRequest(SAMPLE_INPUT_STRING, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals(200,output.httpCode);
        
        output=new AllImplementationsResponse(400, "");
       
    }
}




