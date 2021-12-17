package edu.wpi.cs.proteus.demo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import edu.wpi.cs.proteus.http.GetAllImplementationsResponse;
import edu.wpi.cs.proteus.http.GetImplementationResponse;

public class GetImplementationResponseTest {

	@Test
	public void testConstructor() {
		Map<String, String> implementation = new HashMap<>();
		GetImplementationResponse getAllImplementationsResponse = new GetImplementationResponse(200, implementation);
		assertEquals(implementation, getAllImplementationsResponse.implementation);
		assertEquals(200, getAllImplementationsResponse.statusCode);
	}
}

