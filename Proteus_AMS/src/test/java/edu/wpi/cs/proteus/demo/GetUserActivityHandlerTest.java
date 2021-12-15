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

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
        input = null;
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

    @Test
    public void testGetUserActivityHandler() {
        GetUserActivityHandler handler = new GetUserActivityHandler();
        Context ctx = createContext();

        AllUserActivityResponse output = handler.handleRequest(input, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals("Hello from Lambda!", output);
    }
}
