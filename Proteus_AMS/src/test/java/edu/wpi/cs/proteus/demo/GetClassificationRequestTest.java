package edu.wpi.cs.proteus.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.proteus.http.AddImplementationRequest;
import edu.wpi.cs.proteus.http.GetAllImplementationsRequest;
import edu.wpi.cs.proteus.http.GetClassificationByIdRequest;
import edu.wpi.cs.proteus.http.GetClassificationRequest;
import edu.wpi.cs.proteus.http.SimpleImplementationRequest;

public class GetClassificationRequestTest {

	GetClassificationRequest getClassificationRequest;
	
	@Test
	public void testConstructor() {
		getClassificationRequest = new GetClassificationRequest("testName");
		assertEquals("testName", getClassificationRequest.getClassificationName());
	}
}

