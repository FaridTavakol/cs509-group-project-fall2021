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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
			Map<String, String> message = new HashMap<>();
			message.put("implementationURL", implementation.getUrl());
			message.put("implementationLanguage", implementation.getLanguage());
			message.put("implementationDetails", implementation.getDetails());
			GetImplementationResponse response = new GetImplementationResponse(200, message);
			
    		writer.write(gson.toJson(response));
        }
        catch (Exception exception) {
        	Response response = new Response(400, "Failed to get implementation. " + exception.getMessage());
        	logger.log(exception.toString());
        	writer.write(gson.toJson(response));
        }
        finally {
        	reader.close();
        	writer.close();
        }
    }

}
