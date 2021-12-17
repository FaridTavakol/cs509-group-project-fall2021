package edu.wpi.cs.proteus.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import edu.wpi.cs.proteus.http.Response;
import edu.wpi.cs.proteus.model.Benchmark;

public class RemoveBenchmarkHandlerTest {

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
    public void testRemoveBenchmarkHandler() {
        RemoveBenchmarkHandler handler = new RemoveBenchmarkHandler();
        Context ctx = createContext();

        String SAMPLE_INPUT_STRING = "{\"ID\":\"4\",\"requestedBy\":\"A\"}";
        Response output = handler.handleRequest(SAMPLE_INPUT_STRING, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals(200,output.statusCode);
        
        
       
    }
}


