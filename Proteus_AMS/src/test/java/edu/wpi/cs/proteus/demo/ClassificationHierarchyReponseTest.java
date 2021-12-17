package edu.wpi.cs.proteus.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.proteus.http.AddImplementationRequest;
import edu.wpi.cs.proteus.http.ClassificationHierarchyResponse;
import edu.wpi.cs.proteus.http.GetAllImplementationsRequest;
import edu.wpi.cs.proteus.http.GetClassificationByIdRequest;
import edu.wpi.cs.proteus.http.SimpleImplementationRequest;

public class ClassificationHierarchyReponseTest {

	ClassificationHierarchyResponse classificationHierarchyResponseTest;

    @Before  
    public void setUp() throws Exception {  
    	classificationHierarchyResponseTest = new ClassificationHierarchyResponse(400, "testError");
    }  
    
	
	@Test
	public void testId() {
		assertEquals("Response(0)", classificationHierarchyResponseTest.toString());
	}
}

