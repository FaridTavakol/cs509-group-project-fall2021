package edu.wpi.cs.proteus.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import edu.wpi.cs.proteus.http.Response;
import edu.wpi.cs.proteus.model.Benchmark;

public class AddBenchmarkHandlerTest {
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
    public void testAddBenchmarkHandler() {
        AddBenchmarkHandler handler = new AddBenchmarkHandler();
        Context ctx = createContext();

        String SAMPLE_INPUT_STRING = "{\"implemetationid\":\"3b565352-3446-4fdc-a19a-ebb1e092042e\",\"probleminstanceid\":\"21\",\"name\":\"Testing\",\"time\":\"12\",\"cpu\":\"x86-64\",\"cores\":\"Single\",\"ram\":\"2GB\",\"cache\":\"L1\",\"requestedBy\":\"A\"}";
        Response output = handler.handleRequest(SAMPLE_INPUT_STRING, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals(200,output.statusCode);
        
        Benchmark b = new Benchmark();
        b.setImplemetationid("1");
        b.setProbleminstanceid("2");
        b.setName("ABC");
        b.setTime("12");
        b.setCache("L1");
        b.setCpu("x1");
        b.setCores("3");
        b.setRam("1");
        b.setRequestedBy("requestedBy");
        
        b= new Benchmark("Test", "Test","Test","Test","Test","Test","Test","Test","Test","Test");
        b= new Benchmark("Test","Test","Test","Test","Test","Test","Test","Test","Test");
        
    }
}
