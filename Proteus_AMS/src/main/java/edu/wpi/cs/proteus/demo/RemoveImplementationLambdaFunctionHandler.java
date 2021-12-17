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

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.wpi.cs.proteus.db.ImplementationsDAO;
import edu.wpi.cs.proteus.db.LogDAO;
import edu.wpi.cs.proteus.http.Response;
import edu.wpi.cs.proteus.http.SimpleImplementationRequest;
import edu.wpi.cs.proteus.model.Implementation;
import edu.wpi.cs.proteus.model.Log;

public class RemoveImplementationLambdaFunctionHandler implements RequestStreamHandler {

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
    		logger.log("Loading Lambda handler of RemoveImplementation");
    		
    		boolean result = removeImplementation(simpleImplementationRequest);
	        
			if(result) {
				String response = "Implementation removed successfully!";
				writer.write(gson.toJson(new Response(200, response)));	
			} else {
				writer.write(gson.toJson(new Response(400, "Failed to remove implementation from the database.")));
			}
        }
        catch (Exception exception) {
        	Response response = new Response(400, "Failed to remove Implementation: " +  exception.getMessage());
        	logger.log(exception.toString());
        	writer.write(gson.toJson(response));
        }
        finally {
        	reader.close();
        	writer.close();
        }
    }

    private boolean removeImplementation(SimpleImplementationRequest simpleImplementationRequest) throws Exception {
    	try {
			ImplementationsDAO implementationsDAO = new ImplementationsDAO();
			String requestedBy = simpleImplementationRequest.getRequestedBy();
			
			boolean result = implementationsDAO.removeImplementation(simpleImplementationRequest.getImplementationID());
			
			if (result) {
				Log entry = new Log(requestedBy, "Remove Implementation", java.time.LocalDate.now().toString());
				LogDAO ldao = new LogDAO();
				return ldao.addLogEntry(entry);
			}
    	} catch (Exception e) {
    		throw new Exception(e.getMessage());
    	}
		
    	return false;
    }
}
