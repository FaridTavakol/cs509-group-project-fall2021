package edu.wpi.cs.proteus.demo;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;

import edu.wpi.cs.proteus.db.ImplementationsDAO;
import edu.wpi.cs.proteus.http.SimpleImplementationRequest;
import edu.wpi.cs.proteus.http.Response;
import edu.wpi.cs.proteus.model.Implementation;

public class DownloadImplementationLambdaFunctionHandler implements RequestHandler<SimpleImplementationRequest, Response>{

    LambdaLogger logger;
    @Override
    public Response handleRequest(SimpleImplementationRequest input, Context context) {

		logger = context.getLogger();
    	logger.log("Input: " + input.toString());
		logger.log("Loading Java Lambda handler of AddImplementationRequestHandler");
		
		Gson gson = new GsonBuilder().create();
		SimpleImplementationRequest simpleImplementationRequest = null;

		simpleImplementationRequest = gson.fromJson(input.toString(), SimpleImplementationRequest.class);

		Response response=null;
		
		try {
			ImplementationsDAO implementationsDAO = new ImplementationsDAO();
			Implementation implementation = implementationsDAO.getImplementation(simpleImplementationRequest.getImplementationID());
			String fileURL = implementation.getUrl();
			String accessURL = generatePresignedURL(fileURL);

			response = new Response(200, accessURL);	
		} catch (Exception e) {
			String failMessage = "Failed to get implementation. " + e.getMessage();
			response = new Response(400, failMessage);
		}

		return response; 
    }

    public static String generatePresignedURL(String key) throws IOException {
        Regions clientRegion = Regions.DEFAULT_REGION;
        String bucketName = "proteus-implementations";
        String objectKey = key;

        try {
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(clientRegion)
                    .withCredentials(new ProfileCredentialsProvider())
                    .build();

            // Set the presigned URL to expire after one hour.
            java.util.Date expiration = new java.util.Date();
            long expTimeMillis = Instant.now().toEpochMilli();
            expTimeMillis += 1000 * 60 * 60;
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
        }
		return "ERROR: Unable to generate URL for file.";
    }
}