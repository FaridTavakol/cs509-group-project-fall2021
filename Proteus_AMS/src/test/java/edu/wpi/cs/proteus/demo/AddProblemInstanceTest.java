package edu.wpi.cs.proteus.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import edu.wpi.cs.proteus.http.PIRequest;
import edu.wpi.cs.proteus.http.Response;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class AddProblemInstanceTest {

    private static PIRequest input;

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
        AddProblemInstance handler = new AddProblemInstance();
        Context ctx = createContext();

        PIRequest req= new PIRequest("24bc9b69-028b-4a70-b0ec-0e3f26d6088f$Testing$Testing","","","Admin.html","testing");
        Response output = handler.handleRequest(req, ctx);

        // TODO: validate output here if needed.
       Assert.assertEquals(200, output.statusCode);
       req=new PIRequest();
       req.setAlgorithmID("1$a$a");
       req.setURL("Example");
       req.setName("");
       req.setDescription("");
       req.setFileContent("Example");
       
       output = handler.handleRequest(req, ctx);
       Assert.assertEquals(200, output.statusCode);
       
       req= new PIRequest("24bc9b69-028b-4a70-b0ec-0e3f26d6088f$Testing$Testing","","","Admin.html","testing","A");
       output = handler.handleRequest(req, ctx);
       Assert.assertEquals(200, output.statusCode);
      
    }
}
