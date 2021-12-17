package edu.wpi.cs.proteus.demo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import edu.wpi.cs.proteus.http.GetAllImplementationsResponse;
import edu.wpi.cs.proteus.http.GetClassificationByIdResponse;

public class GetClassificationByIdReponseTest {

	@Test
	public void testConstructor() {
		GetClassificationByIdResponse getClassificationByIdResponse = new GetClassificationByIdResponse("testName");
		assertEquals("Success!", getClassificationByIdResponse.errorMessage);
		assertEquals("200", getClassificationByIdResponse.statusCode);
		assertEquals("testName", getClassificationByIdResponse.classificationName);
	}
}

