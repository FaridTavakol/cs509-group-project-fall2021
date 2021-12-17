package edu.wpi.cs.proteus.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import edu.wpi.cs.proteus.db.ClassificationDAO;
import edu.wpi.cs.proteus.http.GetClassificationResponse;
import edu.wpi.cs.proteus.http.MergeClassificationRequest;
import edu.wpi.cs.proteus.http.MergeClassificationResponse;

public class MergeClassifcationHandlerTest {

	private static MergeClassificationRequest input;

	@BeforeClass
	public static void createInput() throws IOException {
		// TODO: set up your sample input object here.
		input = null;
	}

	private Context createContext() {
		TestContext ctx = new TestContext();

		// TODO: customize your context here if needed.
		ctx.setFunctionName("compute");

		return ctx;
	}

	@Test
	public void testMergeClassifcationHandler() throws Exception {
		MergeClassificationHandler handler = new MergeClassificationHandler();
		Context ctx = createContext();

		input=new MergeClassificationRequest("test1", "test2");
		input.setClassificationOne("test3");
		input.setClassificationTwo("Add");
		input.getClassificationOne();
		input.getClassificationTwo();
		
		MergeClassificationResponse output = handler.handleRequest(input, ctx);

		// TODO: validate output here if needed.
		Assert.assertEquals(200, output.httpCode);
		output.toString();

		output = new MergeClassificationResponse("A");
		output.toString();
		
		input=new MergeClassificationRequest("test3", "sample");
		
		output = handler.handleRequest(input, ctx);

		// TODO: validate output here if needed.
		Assert.assertEquals(400, output.httpCode);
		
		ClassificationDAO dao= new ClassificationDAO();
		dao.getAllClassifications();
		
		
	}
}

