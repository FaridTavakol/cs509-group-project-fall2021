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

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.wpi.cs.proteus.db.ImplementationsDAO;
import edu.wpi.cs.proteus.db.LogDAO;
import edu.wpi.cs.proteus.http.GetAllImplementationsRequest;
import edu.wpi.cs.proteus.http.GetAllImplementationsResponse;
import edu.wpi.cs.proteus.http.Response;
import edu.wpi.cs.proteus.model.Implementation;
import edu.wpi.cs.proteus.model.Log;

public class GetAllImplementationsLambdaFunctionHandler implements RequestStreamHandler {

	Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        LambdaLogger logger = context.getLogger();;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("US-ASCII")));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream, Charset.forName("US-ASCII"))));
        GetAllImplementationsRequest getAllImplementationsRequest = null;
        
        try {
        	getAllImplementationsRequest = gson.fromJson(reader, GetAllImplementationsRequest.class);
        	logger.log("Stream Type: " + inputStream.getClass().toString());
        	logger.log("Input: " + reader.toString());
    		logger.log("Loading Lambda handler of GetImplementation");
    		
    		List<Map<String, String>> result = getAllImplementations(getAllImplementationsRequest);
			
			if (result != null) {
				writer.write(gson.toJson(new GetAllImplementationsResponse(200, result)));
			} else {
				writer.write(gson.toJson(new Response(400, "Failed to get all implementations.")));
			}
        }
        catch (Exception exception) {
        	Response response = new Response(400, "Request: " + getAllImplementationsRequest.toString() + ". Failed to get implementation. " + exception.getMessage());
        	logger.log(exception.toString());
        	writer.write(gson.toJson(response));
        }
        finally {
        	reader.close();
        	writer.close();
        }
    }
    
    private List<Map<String, String>> getAllImplementations(GetAllImplementationsRequest getAllImplementationsRequest) throws Exception {
    	ImplementationsDAO implementationsDAO = new ImplementationsDAO();
		List<Implementation> implementations;
		String requestedBy = getAllImplementationsRequest.getRequestedBy();
		
		try {
			String algorithmID = getAllImplementationsRequest.getAlgorithmID();
			implementations = implementationsDAO.getAllImplementations(algorithmID);
			
			if (implementations != null) {
				List<Map<String, String>> message = new ArrayList<>();
				for (Implementation i : implementations) {
					Map<String, String> map = new HashMap<>();
					map.put("implementationLanguage", i.getLanguage());
					map.put("implementationID", i.getId());
					message.add(map);
				}
				
				if (message.size() > 0) {
					Log entry = new Log(requestedBy, "Get All Implementations", java.time.LocalDate.now().toString());
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
