package edu.wpi.cs.proteus.demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.wpi.cs.proteus.db.ImplementationsDAO;
import edu.wpi.cs.proteus.http.SimpleImplementationRequest;
import edu.wpi.cs.proteus.http.GetImplementationResponse;
import edu.wpi.cs.proteus.http.Response;
import edu.wpi.cs.proteus.http.ResponseImplementation;
import edu.wpi.cs.proteus.model.Implementation;

public class GetImplementationLambdaFunctionHandler implements RequestStreamHandler {

	Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        LambdaLogger logger = context.getLogger();;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("US-ASCII")));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream, Charset.forName("US-ASCII"))));
        
        try {
        	SimpleImplementationRequest simpleImplementationRequest = gson.fromJson(reader, SimpleImplementationRequest.class);
        	logger.log("Stream Type: " + inputStream.getClass().toString());
        	logger.log("Input: " + reader.toString());
    		logger.log("Loading Lambda handler of GetImplementation");
    		
			ImplementationsDAO implementationsDAO = new ImplementationsDAO();
			Implementation implementation = implementationsDAO.getImplementation(simpleImplementationRequest.getImplementationID());
			String id = implementation.getId();
			String url = implementation.getUrl();
			String language = implementation.getLanguage();
			String details = implementation.getDetails();
			String algorithmID = implementation.getAlgorithmID();
			ResponseImplementation responseImplementation = new ResponseImplementation(id, url, details, language, algorithmID);
			GetImplementationResponse response = new GetImplementationResponse(200, responseImplementation);
			
    		writer.write(gson.toJson(response));
        }
        catch (Exception exception) {
        	List<String> strings1 = new ArrayList<String>();
        	Stream<String> strings2 = reader.lines();
        	strings2.forEach(s -> strings1.add(s));
        	String input = "";
        	int counter = 1;
        	for(String s : strings1) {
        		input = Integer.toString(counter) + ". " + input + s + " ";
        		counter++;
        	}
        	Response response = new Response(400, "Input: " + input + ". Failed to get implementation.");
        	logger.log(exception.toString());
        	writer.write(gson.toJson(response));
        }
        finally {
        	reader.close();
        	writer.close();
        }
    }

}
