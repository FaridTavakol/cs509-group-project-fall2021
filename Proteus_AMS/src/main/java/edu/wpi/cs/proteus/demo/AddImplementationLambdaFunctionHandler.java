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
import java.util.UUID;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.wpi.cs.proteus.db.ImplementationsDAO;
import edu.wpi.cs.proteus.db.LogDAO;
import edu.wpi.cs.proteus.http.AddImplementationRequest;
import edu.wpi.cs.proteus.http.Response;
import edu.wpi.cs.proteus.model.Algorithm;
import edu.wpi.cs.proteus.model.Implementation;
import edu.wpi.cs.proteus.model.Log;

public class AddImplementationLambdaFunctionHandler implements RequestStreamHandler {

	Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        LambdaLogger logger = context.getLogger();;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("US-ASCII")));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream, Charset.forName("US-ASCII"))));
        
        try {
        	AddImplementationRequest addImplementationRequest = gson.fromJson(reader, AddImplementationRequest.class);
        	logger.log("Stream Type: " + inputStream.getClass().toString());
        	logger.log("Input: " + reader.toString());
    		logger.log("Loading Lambda handler of AddImplementation");
    		
    		String implementationID = UUID.randomUUID().toString();
			boolean result = addImplementation(addImplementationRequest, implementationID);
			
			if(result) {
				writer.write(gson.toJson(new Response(200, implementationID)));	
			} else {
				writer.write(gson.toJson(new Response(400, "Failed to add implementation to the database.")));
			}
        }
        catch (Exception exception) {
        	Response response = new Response(400, "Failed to add Implementation: " + exception.getMessage());
        	logger.log(exception.toString());
        	writer.write(gson.toJson(response));
        }
        finally {
        	reader.close();
        	writer.close();
        }
    }
    
    private boolean addImplementation(AddImplementationRequest addImplementationRequest, String implementationID) throws Exception {
    	ImplementationsDAO implementationsDAO = new ImplementationsDAO();
		String algorithmID = addImplementationRequest.getAlgorithmID();
		String language = addImplementationRequest.getLanguage();
		String url = addImplementationRequest.getUrl();
		String details = addImplementationRequest.getDetails();
		String requestedBy = addImplementationRequest.getRequestedBy();
		
		try {
			Implementation newImplementation = new Implementation(implementationID, url, details, language, algorithmID, new ArrayList<String>());
			
			boolean result = implementationsDAO.addImplementation(newImplementation);
			
			if (result) {
				Log entry = new Log(requestedBy, "Add Implementation", java.time.LocalDate.now().toString());
				LogDAO ldao = new LogDAO();
				return ldao.addLogEntry(entry);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return false;
    }

}
