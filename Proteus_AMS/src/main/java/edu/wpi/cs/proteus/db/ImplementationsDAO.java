package edu.wpi.cs.proteus.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs.proteus.model.Implementation;

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

	public List<Implementation> getAllImplementations(String algorithmID) throws Exception {
		try {
			Statement stmt = conn.createStatement();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE algorithmID=?;");
			ps.setString(1, algorithmID);
			ResultSet resultSet = ps.executeQuery();
			List<Implementation> implementations = new ArrayList<>();

			if (resultSet != null) {
				while (resultSet.next()) {
					Implementation implementation = generateImplementation(resultSet);
					implementations.add(implementation);
				}
				resultSet.close();
				stmt.close();
				
				return implementations;
			}

			return null;
		} catch (Exception e) {
			throw new Exception("Failed in getting implementations by AlgoID: " + e.getMessage());
		}
	}

	public Implementation getImplementation(String implementationID) throws Exception {
		try {
			Implementation implementation = null;
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE implementationID=?;");
			ps.setString(1, implementationID);
			ResultSet resultSet = ps.executeQuery();

			if (resultSet != null) {
				resultSet.next();
				implementation = generateImplementation(resultSet);
				resultSet.close();
				ps.close();
				return implementation;
			}

			else
				return null;

		} catch (Exception e) {
			throw new Exception("Failed getting implementation for id= " + implementationID + ": " + e.getMessage());
		}
	}

	public boolean addImplementation(Implementation newImplementation) throws Exception {
		try {
			PreparedStatement ps = conn
					.prepareStatement("INSERT INTO " + tblName + " (implementationID,algorithmID,url,language,details) VALUES (?,?,?,?,?);");
			ps.setString(1, newImplementation.getId());
			ps.setString(2, newImplementation.getAlgorithmID());
			ps.setString(3, newImplementation.getUrl());
			ps.setString(4, newImplementation.getLanguage());
			ps.setString(5, newImplementation.getDetails());
			ps.execute();
			ps.close();
			return true;
		} catch (Exception e) {
			throw new Exception("Failed to add implementation. " + e.getMessage());
		}
	}

	// TODO UpdateImplementation

	public boolean removeImplementation(String implementationID) throws Exception {
		try {
			PreparedStatement ps = conn
					.prepareStatement("DELETE FROM " + tblName + " WHERE implementationID = ?");
			ps.setString(1, implementationID);
			ps.execute();
			return true;
		} catch (Exception e) {
			throw new Exception("Failed to remove implementation: " + implementationID + ". " + e.getMessage());
		} 
	}

	private Implementation generateImplementation(ResultSet resultSet) throws Exception {
		String id = resultSet.getString("implementationID");
		String url = resultSet.getString("url");
		String details = resultSet.getString("details");
		String language = resultSet.getString("language");
		String algorithmID = resultSet.getString("algorithmID");
		List<String> benchmarks = new ArrayList<>(); //new BenchmarksDAO().getBenchmarksForImplementation(id);
		return new Implementation(id, url, details, language, algorithmID, benchmarks);
	}

}