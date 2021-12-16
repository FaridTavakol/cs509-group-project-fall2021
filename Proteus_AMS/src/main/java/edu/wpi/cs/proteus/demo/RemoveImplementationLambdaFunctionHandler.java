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
import edu.wpi.cs.proteus.http.Response;
import edu.wpi.cs.proteus.http.SimpleImplementationRequest;
import edu.wpi.cs.proteus.model.Implementation;

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
    		
			ImplementationsDAO implementationsDAO = new ImplementationsDAO();
			Implementation implementation = implementationsDAO.getImplementation(simpleImplementationRequest.getImplementationID());
			
			boolean result = implementationsDAO.removeImplementation(simpleImplementationRequest.getImplementationID());
			//boolean deleteResult = removeFileFromS3(implementation.getUrl());
	        
			if(result) {
				String response = "Implementation removed successfully!";
				//if (!deleteResult) response = response + " But failed to remove file from S3.";
				writer.write(gson.toJson(new Response(200, response)));	
			} else {
				writer.write(gson.toJson(new Response(400, "Failed to remove implementation from the database.")));
			}
        }
        catch (Exception exception) {
        	Response response = new Response(400, exception.getMessage());
        	logger.log(exception.toString());
        	writer.write(gson.toJson(response));
        }
        finally {
        	reader.close();
        	writer.close();
        }
    }

    
    public static boolean removeFileFromS3(String key) throws IOException {
        Regions clientRegion = Regions.US_EAST_2;
        String bucketName = "proteus-implementations";
        String objectKey = key;
    	final String accessKey;
    	final String secretKey;
    	AmazonS3 s3Client = null;

        try {
        	accessKey = System.getenv("accessKey");
    		if (accessKey == null) {
    			System.err.println("Environment variable accessKey is not set!");
    		}
    		secretKey = System.getenv("secretKey");
    		if (secretKey == null) {
    			System.err.println("Environment variable secretKey is not set!");
    		}
    		
            s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(clientRegion)
                    .withPathStyleAccessEnabled(true)
                    .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                    .build();

        	s3Client.deleteObject(bucketName, objectKey);

            return true;
        } catch (AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process 
            // it, so it returned an error response.
            e.printStackTrace();
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            e.printStackTrace();
        } finally {
            if(s3Client != null) {
            	s3Client.shutdown();
            }           
        }
        
		return false;
    }
}
