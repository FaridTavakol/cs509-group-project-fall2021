package edu.wpi.cs.proteus.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;

import edu.wpi.cs.proteus.db.BenchmarkDAO;
import edu.wpi.cs.proteus.db.LogDAO;
import edu.wpi.cs.proteus.http.GetObjectsByID;
import edu.wpi.cs.proteus.http.Response;
import edu.wpi.cs.proteus.model.Log;

public class RemoveBenchmarkHandler implements RequestHandler<Object, Response> {

	LambdaLogger logger;

	@Override
	public Response handleRequest(Object input, Context context) {
		context.getLogger().log("Input: " + input);
		Response response;

		Gson gson = new Gson();
		GetObjectsByID instance = gson.fromJson(input.toString(), GetObjectsByID.class);

		try {

			if (deleteBenchmark(instance.getID(), instance.getRequestedBy())) {
				response = new Response(200, "Benchmark Deleted Successfully");
			} else {
				response = new Response(400, "Error Occurred");
			}
		} catch (Exception e) {
			response = new Response(400, e.getMessage());
		}

		return response;
	}

	boolean deleteBenchmark(String ID, String requestedBy) throws Exception {
		if (logger != null) {
			logger.log("in Delete Problem Instance");
		}
		BenchmarkDAO dao = new BenchmarkDAO();
		System.out.println("connected to DB");

		boolean result = dao.deleteBenchmark(Integer.parseInt(ID));

		if (result) {
			Log entry = new Log(requestedBy, "Delete Benchmark", java.time.LocalDate.now().toString());
			LogDAO ldao = new LogDAO();
			return ldao.addLogEntry(entry);
		}
		return result;

	}

}
