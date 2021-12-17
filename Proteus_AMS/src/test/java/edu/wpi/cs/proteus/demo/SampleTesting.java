package edu.wpi.cs.proteus.demo;

import org.junit.Test;

import com.sun.org.apache.bcel.internal.generic.ANEWARRAY;

import edu.wpi.cs.proteus.http.AddAlgorithmRequest;
import edu.wpi.cs.proteus.http.AddAlgorithmResponse;
import edu.wpi.cs.proteus.http.AddClassificationRequest;
import edu.wpi.cs.proteus.http.AddClassificationResponse;
import edu.wpi.cs.proteus.http.AllAlgorithmsResponse;
import edu.wpi.cs.proteus.http.AllClassificationsResponse;
import edu.wpi.cs.proteus.http.ClassificationHierarchyResponse;
import edu.wpi.cs.proteus.http.DeleteAlgorithmRequest;
import edu.wpi.cs.proteus.http.GetAlgorithmByIdRequest;
import edu.wpi.cs.proteus.http.GetAlgorithmByIdResponse;
import edu.wpi.cs.proteus.http.GetAlgorithmIdByNameRequest;
import edu.wpi.cs.proteus.http.GetAlgorithmIdByNameResponse;
import edu.wpi.cs.proteus.http.GetClassificationResponse;
import edu.wpi.cs.proteus.http.MergeClassificationRequest;
import edu.wpi.cs.proteus.http.MergeClassificationResponse;
import edu.wpi.cs.proteus.http.ReclassifyAlgorithmRequest;

public class SampleTesting {

	@Test
	public void testDeleteProblemInstance() {

		AddAlgorithmRequest a = new AddAlgorithmRequest();
		a = new AddAlgorithmRequest("A", "B");
		a = new AddAlgorithmRequest("1", "q", "q");
		a.setAlgorithmId("a");
		a.setAlgorithmName("a");
		a.setClassificationId("a");
		a.getAlgorithmId();
		a.getAlgorithmName();
		a.getClassificationId();
		a.toString();

		AddAlgorithmResponse a1 = new AddAlgorithmResponse("a");
		a1 = new AddAlgorithmResponse("A", 2);
		a1.toString();

		AddClassificationRequest a2 = new AddClassificationRequest();
		a2.setClassificationName("a");
		a2.setSuperClassification("a3");
		a2.toString();

		AddClassificationResponse a3 = new AddClassificationResponse("s");
		a3.toString();
		
		AllAlgorithmsResponse a4 = new AllAlgorithmsResponse("S",200);
		a4.equals(new AllAlgorithmsResponse("s",200));
		a4.toString();
		
		AllClassificationsResponse a5 = new AllClassificationsResponse(100,"S");
		a5.toString();
		
		ClassificationHierarchyResponse a6=new ClassificationHierarchyResponse(100,"S");
		a6.toString();
		
		DeleteAlgorithmRequest a7=new DeleteAlgorithmRequest();
		a7.setAlgorithmID("b");
		a7.toString();
		
		GetAlgorithmByIdRequest a8=new GetAlgorithmByIdRequest();
		a8.setID("a");
		
		GetAlgorithmByIdResponse a9 = new GetAlgorithmByIdResponse();
		a9 = new GetAlgorithmByIdResponse("a");
		a9=new GetAlgorithmByIdResponse("name","algoId","ClassId");
		
		GetAlgorithmIdByNameResponse a10=new GetAlgorithmIdByNameResponse("A","100");
		a10=new GetAlgorithmIdByNameResponse();

		MergeClassificationRequest b1= new MergeClassificationRequest();
		b1.toString();
		
		MergeClassificationResponse b2=new MergeClassificationResponse("S");
		b2.toString();
		
		
		ReclassifyAlgorithmRequest raa = new ReclassifyAlgorithmRequest("algorithmId","classificationId");
		raa=new ReclassifyAlgorithmRequest();
		raa.getAlgorithmId();
		raa.getClassificationId();
		
		GetAlgorithmIdByNameRequest dd=new GetAlgorithmIdByNameRequest();
		dd.toString();
		
		GetAlgorithmIdByNameResponse ab=new GetAlgorithmIdByNameResponse("A");
		
		GetClassificationResponse qw=new GetClassificationResponse(100,"1");
		ab.toString();
		
	}
}
