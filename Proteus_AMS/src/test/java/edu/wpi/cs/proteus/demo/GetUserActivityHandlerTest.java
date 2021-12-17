package edu.wpi.cs.proteus.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import edu.wpi.cs.proteus.http.AllUserActivityResponse;
import edu.wpi.cs.proteus.http.Response;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class GetUserActivityHandlerTest {

    private static Object input;

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("compute");

        return ctx;
    }

    @Test
    public void testGetUserActivityHandler() {
        GetUserActivityHandler handler = new GetUserActivityHandler();
        Context ctx = createContext();

        String SAMPLE_INPUT_STRING = "{\"email\": \"atifa_sarwar@hotmail.com\", \"password\": \"1234\"}";
        
        AllUserActivityResponse output = handler.handleRequest(SAMPLE_INPUT_STRING, ctx);
        Assert.assertEquals(200, output.httpCode);
        // TODO: validate output here if needed.
        
        output = new AllUserActivityResponse(400, "");
        Assert.assertEquals(400, output.httpCode);
        
    }
}
