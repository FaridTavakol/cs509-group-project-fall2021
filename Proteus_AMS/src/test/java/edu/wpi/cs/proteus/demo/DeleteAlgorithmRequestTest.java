package edu.wpi.cs.proteus.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.proteus.http.AddImplementationRequest;
import edu.wpi.cs.proteus.http.DeleteAlgorithmRequest;
import edu.wpi.cs.proteus.http.GetAlgorithmByIdRequest;
import edu.wpi.cs.proteus.http.GetAllImplementationsRequest;
import edu.wpi.cs.proteus.http.GetClassificationByIdRequest;
import edu.wpi.cs.proteus.http.SimpleImplementationRequest;

public class DeleteAlgorithmRequestTest {

	DeleteAlgorithmRequest deleteAlgorithmRequest;

    @Before  
    public void setUp() throws Exception {  
    	deleteAlgorithmRequest = new DeleteAlgorithmRequest();
    }  
    
	
	@Test
	public void testId() {
		String id = "testId";
		deleteAlgorithmRequest.setAlgorithmID(id);
		assertEquals(id, deleteAlgorithmRequest.getAlgorithmID());
	}
}

