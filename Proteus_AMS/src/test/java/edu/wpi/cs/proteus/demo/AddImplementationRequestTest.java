package edu.wpi.cs.proteus.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.proteus.http.AddImplementationRequest;

public class AddImplementationRequestTest {

	AddImplementationRequest addImplementationRequest;

    @Before  
    public void setUp() throws Exception {  
    	addImplementationRequest = new AddImplementationRequest();
    }  
    
	@Test
	public void testRequestedBy() {
		String id = "testUser";
		addImplementationRequest.setRequestedBy(id);
		assertEquals(id, addImplementationRequest.getRequestedBy());
	}
	
	@Test
	public void testUrl() {
		String url = "testUrl";
		addImplementationRequest.setUrl(url);
		assertEquals(url, addImplementationRequest.getUrl());
	}
	
	@Test
	public void testDetails() {
		String details = "testDetails";
		addImplementationRequest.setDetails(details);
		assertEquals(details, addImplementationRequest.getDetails());
	}
	
	@Test
	public void testLanguage() {
		String language = "testLanguage";
		addImplementationRequest.setLanguage(language);
		assertEquals(language, addImplementationRequest.getLanguage());
	}
	
	@Test
	public void testAlgorithmId() {
		String id = "testAlgorithmId";
		addImplementationRequest.setAlgorithmID(id);
		assertEquals(id, addImplementationRequest.getAlgorithmID());
	}

	@Test
	public void testConstructorAndToString() {
		AddImplementationRequest addImplementationRequest = new AddImplementationRequest("algoId", "language", "details", "url", "testUser");
		assertEquals("testUser", addImplementationRequest.getRequestedBy());
		assertEquals("url", addImplementationRequest.getUrl());
		assertEquals("details", addImplementationRequest.getDetails());
		assertEquals("language", addImplementationRequest.getLanguage());
		assertEquals("algoId", addImplementationRequest.getAlgorithmID());
		assertEquals("AlgorimthID: algoId. Language: language. Details: details. URL: url", addImplementationRequest.toString());
	}
}

