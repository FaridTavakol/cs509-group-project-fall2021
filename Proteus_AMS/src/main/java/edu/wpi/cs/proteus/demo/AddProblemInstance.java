package edu.wpi.cs.proteus.demo;

import java.io.IOException;
import java.io.StringReader;
import java.util.StringTokenizer;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import edu.wpi.cs.proteus.db.LogDAO;
import edu.wpi.cs.proteus.db.PIDAO;
import edu.wpi.cs.proteus.http.PIRequest;
import edu.wpi.cs.proteus.http.Response;
import edu.wpi.cs.proteus.model.Log;
import edu.wpi.cs.proteus.model.PI;


public class AddProblemInstance implements RequestHandler<PIRequest, Response> {

	LambdaLogger logger;

	@Override
	public Response handleRequest(PIRequest input, Context context) {
		
		context.getLogger().log("Input: " + input);
	
//		PI pinstance = new PI();
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, false);
//		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//		String trimmed = input.toString().trim();
//		Gson gson = new Gson();
//		JsonReader reader = new JsonReader(new StringReader(trimmed));
//		
//		try {
//			reader.setLenient(true);
//			pinstance= gson.fromJson(reader, PI.class);
//			
//		}
//		catch(Exception e)
//		{
//			a=e.getMessage();
//		}
		PI pinstance=new PI();
		StringTokenizer st = new StringTokenizer(input.getAlgorithmID(),"$");
		pinstance.setAlgorithmID(st.nextToken());
		pinstance.setName(st.nextToken());
		pinstance.setDescription(st.nextToken());
		pinstance.setURL(input.getURL());
		pinstance.setFileContent(input.getFileContent());
		pinstance.setRequestedBy(input.getRequestedBy());
		Response response;
		try {
			
			
			if (addPI(pinstance)) {
				response = new Response(200,"Problem Instance Successfully added");
			} else {
				response = new Response(400,"Error Occurred");
			}
		}catch (Exception e) { 
			response = new Response(400,e.getMessage());
		}

		return response;
	}
	
	boolean addPI(PI Object) throws Exception {
		if (logger != null) {
			logger.log("in add Problem Instance");
		}
		PIDAO dao = new PIDAO();
		System.out.println("connected to DB");

		boolean result =  dao.addPI(Object);
		
		if(result)
		{
			Log entry = new Log(Object.getRequestedBy(),"Add Problem Instance",java.time.LocalDate.now().toString());
			LogDAO ldao = new LogDAO();
			return ldao.addLogEntry(entry);
		}
		return result;

	}
}
