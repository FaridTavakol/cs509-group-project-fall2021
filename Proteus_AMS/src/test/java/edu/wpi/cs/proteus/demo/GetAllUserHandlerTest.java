package edu.wpi.cs.proteus.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import com.amazonaws.services.lambda.runtime.Context;
import edu.wpi.cs.proteus.http.AllUsersResponse;

public class GetAllUserHandlerTest {
	

    private static Object input;

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("compute");

        return ctx;
    }

    @Test
    public void testGetAllUserHandler() throws IOException {
        GetAllUserHandler handler = new GetAllUserHandler();
        Context ctx = createContext();
        
        AllUsersResponse response = handler.handleRequest(input, ctx);
		Assert.assertEquals(200, response.httpCode);
		
		response=new AllUsersResponse(400, "");
		Assert.assertEquals(400, response.httpCode);
	    
    }
}
