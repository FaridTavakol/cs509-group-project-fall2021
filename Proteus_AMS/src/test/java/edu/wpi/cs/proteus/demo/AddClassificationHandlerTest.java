package edu.wpi.cs.proteus.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import edu.wpi.cs.proteus.http.AddClassificationRequest;
import edu.wpi.cs.proteus.http.AddClassificationResponse;
import edu.wpi.cs.proteus.http.PIRequest;
import edu.wpi.cs.proteus.http.Response;

public class AddClassificationHandlerTest {


    private static AddClassificationRequest input;

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
    public void testAddProblemInstance() {
        AddClassificationHandler handler = new AddClassificationHandler();
        Context ctx = createContext();

        input= new AddClassificationRequest("Testing Sample","test1");
        
        AddClassificationResponse output = handler.handleRequest(input, ctx);

        // TODO: validate output here if needed.
       Assert.assertEquals(200, output.httpCode);
       
       input=new AddClassificationRequest();
       input.setClassificationName("Double Testing");
       input.setSuperClassification("Example");
     
       output = handler.handleRequest(input, ctx);
       Assert.assertEquals(400, output.httpCode);
       
      output.toString();
      input.toString();
    }
}
