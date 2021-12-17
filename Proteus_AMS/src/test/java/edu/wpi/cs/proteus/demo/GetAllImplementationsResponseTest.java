package edu.wpi.cs.proteus.demo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import edu.wpi.cs.proteus.http.GetAllImplementationsResponse;

public class GetAllImplementationsResponseTest {

	@Test
	public void testConstructor() {
		List<Map<String, String>> implementations = new ArrayList<>();
		GetAllImplementationsResponse getAllImplementationsResponse = new GetAllImplementationsResponse(200, implementations);
		assertEquals(implementations, getAllImplementationsResponse.implementations);
		assertEquals(200, getAllImplementationsResponse.statusCode);
	}
}

