package edu.wpi.cs.proteus.demo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.proteus.model.Implementation;

public class ImplementationTest {

	Implementation implementation;
	
    @Before  
    public void setUp() throws Exception {  
        implementation = new Implementation();
    }  
    
	@Test
	public void testId() {
		String id = "testID";
		implementation.setId(id);
		assertEquals(id, implementation.getId());
	}
	
	@Test
	public void testUrl() {
		String url = "testUrl";
		implementation.setUrl(url);
		assertEquals(url, implementation.getUrl());
	}
	
	@Test
	public void testDetails() {
		String details = "testDetails";
		implementation.setDetails(details);
		assertEquals(details, implementation.getDetails());
	}
	
	@Test
	public void testLanguage() {
		String language = "testLanguage";
		implementation.setLanguage(language);
		assertEquals(language, implementation.getLanguage());
	}
	
	@Test
	public void testAlgorithmId() {
		String id = "testAlgorithmId";
		implementation.setAlgorithmID(id);
		assertEquals(id, implementation.getAlgorithmID());
	}
	
	@Test
	public void testBenchmarkIds() {
		List<String> benchmarks = new ArrayList<>();
		benchmarks.add("testbenchmark1");
		benchmarks.add("testbenchmark2");
		implementation.setBenchmarkIDs(benchmarks);
		assertEquals(benchmarks, implementation.getBenchmarkIDs());
	}
	
	@Test
	public void testConstructor() {
		List<String> benchmarks = new ArrayList<>();
		Implementation implementation = new Implementation("id", "url", "details", "language", "algoId", benchmarks);
		assertEquals("id", implementation.getId());
		assertEquals("url", implementation.getUrl());
		assertEquals("details", implementation.getDetails());
		assertEquals("language", implementation.getLanguage());
		assertEquals("algoId", implementation.getAlgorithmID());
		assertEquals(benchmarks, implementation.getBenchmarkIDs());
	}
}

