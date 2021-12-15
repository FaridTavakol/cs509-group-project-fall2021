package edu.wpi.cs.proteus.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;

import edu.wpi.cs.proteus.db.BenchmarkDAO;
import edu.wpi.cs.proteus.db.LogDAO;
import edu.wpi.cs.proteus.http.Response;
import edu.wpi.cs.proteus.model.Benchmark;
import edu.wpi.cs.proteus.model.Log;

public class AddBenchmarkHandler implements RequestHandler<Object, Response> {

	LambdaLogger logger;

	@Override
	public Response handleRequest(Object input, Context context) {
		context.getLogger().log("Input: " + input);

		// TODO: implement your handler
		Gson gson = new Gson();
		Benchmark instance = gson.fromJson(input.toString(), Benchmark.class);
		Response response;
		try {
			if (addBenchmark(instance)) {
				response = new Response(200, "Benchmark Successfully added");
			} else {
				response = new Response(400, "Error Occurred");
			}
		} catch (Exception e) {
			response = new Response(400, e.getMessage());
		}

		return response;
	}

	boolean addBenchmark(Benchmark Object) throws Exception {
		if (logger != null) {
			logger.log("in add Problem Instance");
		}
		BenchmarkDAO dao = new BenchmarkDAO();
		System.out.println("connected to DB");

		boolean result = dao.addBenchmark(Object);

		if (result) {
			Log entry = new Log(Object.getRequestedBy(), "Add Benchmark", java.time.LocalDate.now().toString());
			LogDAO ldao = new LogDAO();
			return ldao.addLogEntry(entry);
		}
		return result;

	}
}
