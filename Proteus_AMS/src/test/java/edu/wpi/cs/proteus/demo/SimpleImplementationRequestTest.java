package edu.wpi.cs.proteus.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.proteus.http.AddImplementationRequest;
import edu.wpi.cs.proteus.http.GetAllImplementationsRequest;
import edu.wpi.cs.proteus.http.SimpleImplementationRequest;

public class SimpleImplementationRequestTest {

	SimpleImplementationRequest simpleImplementationRequest;

    @Before  
    public void setUp() throws Exception {  
    	simpleImplementationRequest = new SimpleImplementationRequest();
    }  
    
	@Test
	public void testRequestedBy() {
		String id = "testUser";
		simpleImplementationRequest.setRequestedBy(id);
		assertEquals(id, simpleImplementationRequest.getRequestedBy());
	}
	
	@Test
	public void testImplementationId() {
		String id = "testImplementationId";
		simpleImplementationRequest.setImplementationID(id);
		assertEquals(id, simpleImplementationRequest.getImplementationID());
	}

	@Test
	public void testConstructorAndToString() {
		SimpleImplementationRequest simpleImplementationRequest = new SimpleImplementationRequest("implementationId", "testUser");
		assertEquals("testUser", simpleImplementationRequest.getRequestedBy());
		assertEquals("implementationId", simpleImplementationRequest.getImplementationID());
	}
}

