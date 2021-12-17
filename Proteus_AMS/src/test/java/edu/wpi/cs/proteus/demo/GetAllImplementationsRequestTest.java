package edu.wpi.cs.proteus.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.proteus.http.AddImplementationRequest;
import edu.wpi.cs.proteus.http.GetAllImplementationsRequest;

public class GetAllImplementationsRequestTest {

	GetAllImplementationsRequest getAllImplementationsRequest;

    @Before  
    public void setUp() throws Exception {  
    	getAllImplementationsRequest = new GetAllImplementationsRequest();
    }  
    
	@Test
	public void testRequestedBy() {
		String id = "testUser";
		getAllImplementationsRequest.setRequestedBy(id);
		assertEquals(id, getAllImplementationsRequest.getRequestedBy());
	}
	
	@Test
	public void testAlgorithmId() {
		String id = "testAlgorithmId";
		getAllImplementationsRequest.setAlgorithmID(id);
		assertEquals(id, getAllImplementationsRequest.getAlgorithmID());
	}

	@Test
	public void testConstructorAndToString() {
		GetAllImplementationsRequest getAllImplementationsRequest = new GetAllImplementationsRequest("algoId", "testUser");
		assertEquals("testUser", getAllImplementationsRequest.getRequestedBy());
		assertEquals("algoId", getAllImplementationsRequest.getAlgorithmID());
		assertEquals("[GetAllImplementationsRequest] algorithmID: algoId, requestedBy: testUser", getAllImplementationsRequest.toString());
	}
}

