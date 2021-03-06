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
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.wpi.cs.proteus.db.AlgorithmsDAO;
import edu.wpi.cs.proteus.db.ImplementationsDAO;
import edu.wpi.cs.proteus.db.LogDAO;
import edu.wpi.cs.proteus.http.GetImplementationResponse;
import edu.wpi.cs.proteus.http.Response;
import edu.wpi.cs.proteus.http.SimpleImplementationRequest;
import edu.wpi.cs.proteus.model.Algorithm;
import edu.wpi.cs.proteus.model.Implementation;
import edu.wpi.cs.proteus.model.Log;

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
    		
			Map<String, String> result = getImplementation(simpleImplementationRequest);
			
			if (result != null) {
				writer.write(gson.toJson(new GetImplementationResponse(200, result)));
			} else {
				writer.write(gson.toJson(new Response(400, "Failed to get implementation.")));
			}
        }
        catch (Exception exception) {
        	Response response = new Response(400, "Failed to get implementation: " + exception.getMessage());
        	logger.log(exception.toString());
        	writer.write(gson.toJson(response));
        }
        finally {
        	reader.close();
        	writer.close();
        }
    }
    
    private Map<String, String> getImplementation(SimpleImplementationRequest simpleImplementationRequest) throws Exception {
    	ImplementationsDAO implementationsDAO = new ImplementationsDAO();
//    	AlgorithmsDAO algorithmsDAO = new AlgorithmsDAO();
		String requestedBy = simpleImplementationRequest.getRequestedBy();
//		Algorithm algorithm = null;
		
		try {
			Implementation implementation = implementationsDAO.getImplementation(simpleImplementationRequest.getImplementationID());
			
			if (implementation != null) {
//				algorithm = algorithmsDAO.getAlgorithmByID(implementation.getAlgorithmID());
				Map<String, String> message = new HashMap<>();
				message.put("implementationURL", implementation.getUrl());
				message.put("implementationLanguage", implementation.getLanguage());
				message.put("implementationDetails", implementation.getDetails());
//				message.put("algorithmName", algorithm.getAlgorithmName());

				if (message.size() > 0) {
					Log entry = new Log(requestedBy, "Get Implementation", java.time.LocalDate.now().toString());
					LogDAO ldao = new LogDAO();
					if (ldao.addLogEntry(entry)) {
						return message;
					}
				}
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return null;
    }

}
