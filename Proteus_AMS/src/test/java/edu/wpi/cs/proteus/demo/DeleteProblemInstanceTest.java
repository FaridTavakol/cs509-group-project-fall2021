package edu.wpi.cs.proteus.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import edu.wpi.cs.proteus.http.GetObjectsByID;
import edu.wpi.cs.proteus.http.Response;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class DeleteProblemInstanceTest {

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
    public void testDeleteProblemInstance() {
        DeleteProblemInstance handler = new DeleteProblemInstance();
        Context ctx = createContext();


        String SAMPLE_INPUT_STRING = "{\"ID\":\"4\",\"requestedBy\":\"A\"}";
        Response output = handler.handleRequest(SAMPLE_INPUT_STRING, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals(200,output.statusCode);
       
        GetObjectsByID a = new GetObjectsByID();
        a.setID("12");
        a.setRequestedBy("ABC");
        
        a = new GetObjectsByID("12","ABC");
    }
}
