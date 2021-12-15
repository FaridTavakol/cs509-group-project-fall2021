package edu.wpi.cs.proteus.demo;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;

import edu.wpi.cs.proteus.db.BenchmarkDAO;
import edu.wpi.cs.proteus.db.LogDAO;
import edu.wpi.cs.proteus.http.AllBenchmarkResponse;
import edu.wpi.cs.proteus.http.AllPIResponse;
import edu.wpi.cs.proteus.model.Benchmark;
import edu.wpi.cs.proteus.model.Log;
import edu.wpi.cs.proteus.model.PI;

public class GetAnImplementationBenchmarkHandler implements RequestHandler<Object, AllBenchmarkResponse> {

	LambdaLogger logger;

	@Override
	public AllBenchmarkResponse handleRequest(Object input, Context context) {
		context.getLogger().log("Input: " + input);

		Gson gson = new Gson();
		Benchmark instance = gson.fromJson(input.toString(), Benchmark.class);
		// TODO: implement your handler

		AllBenchmarkResponse response;
		try {
			List<Benchmark> pinstances = getBenchmarks(instance.getImplemetationid(), instance.getProbleminstanceid());
			response = new AllBenchmarkResponse(pinstances, 200);
		} catch (Exception e) {
			response = new AllBenchmarkResponse(400, "Unable to get Problem Instance(" + e.getMessage() + ")");
		}
		return response;
	}

	List<Benchmark> getBenchmarks(String ImplementationID, String PIID) throws Exception {
		if (logger != null) {
			logger.log("in get Benchmarks");
		}
		BenchmarkDAO dao = new BenchmarkDAO();
		System.out.println("connected to DB");

		return dao.getBenchmark(ImplementationID, PIID);
	}

}
