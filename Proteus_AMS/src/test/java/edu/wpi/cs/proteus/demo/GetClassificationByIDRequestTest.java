package edu.wpi.cs.proteus.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.proteus.http.AddImplementationRequest;
import edu.wpi.cs.proteus.http.GetAllImplementationsRequest;
import edu.wpi.cs.proteus.http.GetClassificationByIdRequest;
import edu.wpi.cs.proteus.http.SimpleImplementationRequest;

public class GetClassificationByIDRequestTest {

	GetClassificationByIdRequest getClassificationByIdRequest;

    @Before  
    public void setUp() throws Exception {  
    	getClassificationByIdRequest = new GetClassificationByIdRequest();
    }  
    
	
	@Test
	public void testId() {
		String id = "testId";
		getClassificationByIdRequest.setClassificationId(id);
		assertEquals(id, getClassificationByIdRequest.getClassificationId());
	}
}

