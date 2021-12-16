package edu.wpi.cs.proteus.demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.Instant;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.wpi.cs.proteus.db.ImplementationsDAO;
import edu.wpi.cs.proteus.http.Response;
import edu.wpi.cs.proteus.http.SimpleImplementationRequest;
import edu.wpi.cs.proteus.model.Implementation;

public class DownloadImplementationLambdaFunctionHandler implements RequestStreamHandler{

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
			String fileURL = implementation.getUrl();
			String accessURL = generatePresignedURL(fileURL);

			if(accessURL != "ERROR") {
				writer.write(gson.toJson(new Response(200, accessURL)));	
			} else {
				writer.write(gson.toJson(new Response(400, "Failed to download implementation.")));
			}
        }
        catch (Exception exception) {
        	Response response = new Response(400, "Failed while handling download implementation request. " + exception.getMessage());
        	logger.log(exception.toString());
        	writer.write(gson.toJson(response));
        }
        finally {
        	reader.close();
        	writer.close();
        }
    }

    public static String generatePresignedURL(String key) throws IOException {
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

            // Set the presigned URL to expire after 5 hours.
            java.util.Date expiration = new java.util.Date();
            long expTimeMillis = Instant.now().toEpochMilli();
            expTimeMillis += 1000 * 60 * 60 * 5;
            expiration.setTime(expTimeMillis);


            // Generate the presigned URL.
            System.out.println("Generating pre-signed URL.");
            GeneratePresignedUrlRequest generatePresignedUrlRequest =
                    new GeneratePresignedUrlRequest(bucketName, objectKey)
                            .withMethod(HttpMethod.GET)
                            .withExpiration(expiration);
            URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
            return url.toString();
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
		return "ERROR";
    }
}