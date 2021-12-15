package edu.wpi.cs.proteus.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs.proteus.model.Benchmark;
public class BenchmarkDAO {

	java.sql.Connection conn;

	public BenchmarkDAO() {
		try {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			conn = null;
		}
	}

	private Benchmark generateBenchmark(ResultSet resultSet) throws Exception {
		String ID = String.valueOf(resultSet.getInt("benchmarkID"));
		String implementationID = resultSet.getString("implementationID");
		String problemInstanceID = resultSet.getString("problemInstanceID");
		String benchmarkName = resultSet.getString("benchmarkName");
		String execTime = resultSet.getString("execTime");
		String CPU = resultSet.getString("CPU");
		String RAM = resultSet.getString("RAM");
		String Cache = resultSet.getString("Cache");
		String Cores = resultSet.getString("Cores");

		return new Benchmark(ID,implementationID,problemInstanceID,benchmarkName,execTime,CPU,Cores,RAM,Cache,"");
	}

	public boolean addBenchmark(Benchmark object) throws Exception {
		try {

			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO Benchmark (implementationID,problemInstanceID,benchmarkName,execTime,CPU,RAM,Cache,Cores) values(?, ?, ?, ?, ?,?,?,?);");
			ps.setString(1, object.getImplemetationid());
			ps.setString(2, object.getProbleminstanceid());
			ps.setString(3, object.getName());
			ps.setString(4, object.getTime());
			ps.setString(5, object.getCpu());
			ps.setString(6, object.getRam());
			ps.setString(7, object.getCache());
			ps.setString(8, object.getCores());
			ps.execute();
			return true;

		} catch (Exception e) {
			throw new Exception("Failed to add BenchMark: " + e.getMessage());
		}
	}

	public List<Benchmark> getBenchmark(String ImplementationID, String PIID) throws Exception {
		try {
			List<Benchmark> instances = new ArrayList<Benchmark>();
			PreparedStatement ps = conn
					.prepareStatement("SELECT * FROM Benchmark WHERE implementationID=? AND problemInstanceID=?;");
			ps.setString(1, ImplementationID);
			ps.setString(2, PIID);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				Benchmark b = generateBenchmark(resultSet);
				instances.add(b);
			}
			resultSet.close();
			ps.close();

			return instances;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting Benchmark: " + e.getMessage());
		}
	}
	
	public boolean deleteBenchmark(int Benchmark_ID) throws Exception {
		try {
		
			PreparedStatement ps = conn.prepareStatement(
					"DELETE FROM Benchmark WHERE benchmarkID = ?;");
			ps.setInt(1, Benchmark_ID);
			ps.execute();
			return true;

		} catch (Exception e) {
			throw new Exception("Failed to delete Benchmark: " + e.getMessage());
		}
	}
	

}
