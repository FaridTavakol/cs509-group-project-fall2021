package edu.wpi.cs.proteus.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import edu.wpi.cs.proteus.http.AllBenchmarkResponse;
import edu.wpi.cs.proteus.http.Response;

public class GetAnImplementationBenchmarkHandlerTest {
	
	
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
    public void testGetAnImplementationBenchmarkHandler() {
    	GetAnImplementationBenchmarkHandler handler = new GetAnImplementationBenchmarkHandler();
        Context ctx = createContext();

        String SAMPLE_INPUT_STRING = "{\"implemetationid\":\"3b565352-3446-4fdc-a19a-ebb1e092042e\",\"probleminstanceid\":\"21\",\"name\":\"Testing\",\"time\":\"12\",\"cpu\":\"x86-64\",\"cores\":\"Single\",\"ram\":\"2GB\",\"cache\":\"L1\",\"requestedBy\":\"A\"}";
        AllBenchmarkResponse output = handler.handleRequest(SAMPLE_INPUT_STRING, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals(200,output.httpCode);
        
        output=new AllBenchmarkResponse(400, "");
       
    }
}



