package edu.wpi.cs.proteus.demo;

import org.junit.Test;

import edu.wpi.cs.proteus.http.AddAlgorithmRequest;
import edu.wpi.cs.proteus.http.AddAlgorithmResponse;

public class SampleTesting {

	@Test
	public void testDeleteProblemInstance() {

		AddAlgorithmRequest a = new AddAlgorithmRequest();
		a = new AddAlgorithmRequest("A", "B");
		a=new AddAlgorithmRequest("1", "q", "q");
		a .setAlgorithmId("a");
		a.setAlgorithmName("a");
		a.setClassificationId("a");
		a.getAlgorithmId();
		a.getAlgorithmName();
		a.getClassificationId();
		a.toString();
		
		AddAlgorithmResponse a1 = new AddAlgorithmResponse("a");
		a1=new AddAlgorithmResponse("A", 2);
			
	}
}
