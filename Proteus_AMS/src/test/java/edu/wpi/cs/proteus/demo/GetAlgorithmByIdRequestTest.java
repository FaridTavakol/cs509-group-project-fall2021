package edu.wpi.cs.proteus.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.proteus.http.AddImplementationRequest;
import edu.wpi.cs.proteus.http.GetAlgorithmByIdRequest;
import edu.wpi.cs.proteus.http.GetAllImplementationsRequest;
import edu.wpi.cs.proteus.http.GetClassificationByIdRequest;
import edu.wpi.cs.proteus.http.SimpleImplementationRequest;

public class GetAlgorithmByIdRequestTest {

	GetAlgorithmByIdRequest getAlgorithmByIdRequest;

    @Before  
    public void setUp() throws Exception {  
    	getAlgorithmByIdRequest = new GetAlgorithmByIdRequest();
    }  
    
	
	@Test
	public void testId() {
		String id = "testId";
		getAlgorithmByIdRequest.setID(id);
		assertEquals(id, getAlgorithmByIdRequest.getID());
	}
}

