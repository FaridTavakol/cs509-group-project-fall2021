package edu.wpi.cs.proteus.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.proteus.http.AddImplementationRequest;
import edu.wpi.cs.proteus.http.DeleteAlgorithmRequest;
import edu.wpi.cs.proteus.http.GetAlgorithmByIdRequest;
import edu.wpi.cs.proteus.http.GetAllImplementationsRequest;
import edu.wpi.cs.proteus.http.GetClassificationByIdRequest;
import edu.wpi.cs.proteus.http.PIRequest;
import edu.wpi.cs.proteus.http.SimpleImplementationRequest;

public class PIRequestTest {

	PIRequest pIRequest;

    @Before  
    public void setUp() throws Exception {  
    	pIRequest = new PIRequest();
    }  
    
	@Test
	public void testRequestedBy() {
		String id = "testId";
		pIRequest.setRequestedBy(id);
		assertEquals(id, pIRequest.getRequestedBy());
	}
	
	@Test
	public void testName() {
		String name = "testName";
		pIRequest.setName(name);
		assertEquals(name, pIRequest.getName());
	}
	
	@Test
	public void testDescription() {
		String decsription = "testDescription";
		pIRequest.setDescription(decsription);
		assertEquals(decsription, pIRequest.getDescription());
	}
}

