package edu.wpi.cs.proteus.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import edu.wpi.cs.proteus.http.RemoveClassificationRequest;
import edu.wpi.cs.proteus.http.RemoveClassificationResponse;
import edu.wpi.cs.proteus.http.Response;

public class RemoveClassificationHandlerTest {

	private static RemoveClassificationRequest input;
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
    public void testRemoveClassificationHandler() {
    	RemoveClassificationHandler handler = new RemoveClassificationHandler();
        Context ctx = createContext();

       
        input=new RemoveClassificationRequest();
        input=new RemoveClassificationRequest("test1");
        input.setClassificationName("test1");
        input.getClassificationName();
        input.toString();
        RemoveClassificationResponse output = handler.handleRequest(input, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals(200,output.httpCode);
        
        output=new RemoveClassificationResponse("S", 111);
        output.toString();
       
    }
}


