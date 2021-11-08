package edu.wpi.cs.proteus.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs.proteus.model.Implementation;
import edu.wpi.cs.proteus.model.Algorithm;
import edu.wpi.cs.proteus.model.Benchmark;

/**
 * Note that CAPITALIZATION matters regarding the table name. If you create with
 * a capital "Constants" then it must be "Constants" in the SQL queries.
 * 
 * @author heineman
 *
 */
public class ImplementationsDAO {

	public java.sql.Connection conn;

	final String tblName = "Implementation"; // Exact capitalization

	public ImplementationsDAO() {
		try {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			conn = null;
		}
	}

	public List<Implementation> getAllImplementations() throws Exception {
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM " + tblName;
			ResultSet resultSet = stmt.executeQuery(query);
			List<Implementation> implementations = new ArrayList<>();

			if (resultSet != null) {
				while (resultSet.next()) {
					Implementation implementation = generateImplementation(resultSet);
					implementations.add(implementation);
				}
				resultSet.close();
				stmt.close();
			}

			return implementations;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting implementations: " + e.getMessage());
		}
	}

	public Implementation getImplementation(String implementationID) throws Exception {
		try {
			Implementation implementation = null;
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE implementationID=?;");
			ps.setString(1, implementationID);
			ResultSet resultSet = ps.executeQuery();

			if (resultSet != null) {
				while (resultSet.next()) {
					implementation = generateImplementation(resultSet);
				}
				resultSet.close();
				ps.close();
				return implementation;
			}

			else
				return null;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting implementation: " + e.getMessage());
		}
	}

	public boolean addImplementation(Implementation newImplementation) throws Exception {
		try {
			PreparedStatement ps = conn
					.prepareStatement("INSERT INTO " + tblName + " (implementationID,algorithmID,implementationName,url,language,details) values(?,?,?,?,?,?);");
			ps.setString(1, newImplementation.getId());
			ps.setString(2, newImplementation.getAlgorithm().getId());
			ps.setString(3, newImplementation.getName());
			ps.setString(4, newImplementation.getUrl());
			ps.setString(5, newImplementation.getLanguage());
			ps.setString(6, newImplementation.getDetails());

			ps.execute();
			return true;

		} catch (Exception e) {
			throw new Exception("Failed to insert constant: " + e.getMessage());
		}
	}

	// TODO UpdateImplementation

	public boolean removeImplementation(Implementation implementation) throws Exception {
		try {
			PreparedStatement ps = conn
					.prepareStatement("DELETE FROM " + tblName + " WHERE implementationID = ? AND algorithmName = ?");
			ps.setString(1, implementation.getId());
			ps.setString(2, implementation.getAlgorithm().getName());
			ps.execute();
			return true;
		} catch (Exception e) {
			throw new Exception("Failed to remove implementation: " + implementation.getId() + ". " + e.getMessage());
		} 
	}

	private Implementation generateImplementation(ResultSet resultSet) throws Exception {
		String id = resultSet.getString("implementationID");
		String url = resultSet.getString("url");
		String name = resultSet.getString("implementationName");
		String details = resultSet.getString("details");
		String language = resultSet.getString("language");
		String algorithmID = resultSet.getString("algorithmID");
		Algorithm algorithm = new AlgorithmsDAO().getAlgorithm(algorithmID);
		List<Benchmark> benchmarks = new BenchmarksDAO().getBenchmarksForImplementation(id);
		return new Implementation(id, url, name, details, language, algorithm, benchmarks);
	}

}