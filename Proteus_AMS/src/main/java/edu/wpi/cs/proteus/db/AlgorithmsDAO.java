package edu.wpi.cs.proteus.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs.proteus.model.Algorithm;

public class AlgorithmsDAO {

	// Members
	public java.sql.Connection conn;
	final String tblName = "Algorithms";

	// Constructor
	public AlgorithmsDAO()
	{
		try
		{
			conn = DatabaseUtil.connect();
		} catch (Exception e)
		{
			conn = null;
		}
	}

	// Methods //
	
	public Algorithm getAlgorithm(String valueName, String value) throws Exception
	{

		try
		{
			Algorithm algorithm = null;
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE " + valueName + "=?;");
			ps.setString(1, value);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next())
			{
				algorithm = generateAlgorithm(resultSet);
			}
			resultSet.close();
			ps.close();

			return algorithm;

		} catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception("Failed in getting playlist: " + e.getMessage());
		}
	}

	public Algorithm getAlgorithmByName(String name) throws Exception
	{
		return getAlgorithm("algorithmName", name);
	}
	
	public Algorithm getAlgorithmByID(String id) throws Exception
	{

		return getAlgorithm("algorithmId", id);
	}

	public List<Algorithm> getAllAlgorithms() throws Exception
	{
		List<Algorithm> algorithms = new ArrayList<>();
		try
		{

			Statement statement = conn.createStatement();
			String query = "SELECT * FROM " + tblName;
			ResultSet resultSet = statement.executeQuery(query);
			resultSet.getFetchSize();

			while (resultSet.next())
			{
				Algorithm a = generateAlgorithm(resultSet);
				algorithms.add(a);
			}
			resultSet.close();
			statement.close();

			return algorithms;

		} catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception("Failed in getting all Algorithms: " + e.getMessage());
		}
	}

	public boolean addAlgorithm(Algorithm algorithm_) throws Exception
	{

		try
		{
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE algorithmId = ?;");
			ps.setString(1, algorithm_.getAlgorithmId());
			ResultSet resultSet = ps.executeQuery();

			// IF Algorithm already exists
			while (resultSet.next())
			{
				Algorithm a = generateAlgorithm(resultSet);
				resultSet.close();
				return false;
			}

			ps = conn.prepareStatement(
					"INSERT INTO " + tblName + " (algorithmName, classificationId, algorithmId) values(?,?,?);");
			ps.setString(1, algorithm_.getAlgorithmName());
			ps.setString(2, algorithm_.getClassificationId());
			ps.setString(3, algorithm_.getAlgorithmId());
			ps.execute();
			return true;

		} catch (Exception e)
		{
			throw new Exception("Failed to add Algorithm: " + e.getMessage());
		}
	}

	public boolean deleteAlgorithm(Algorithm algorithm_) throws Exception
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("DELETE FROM " + tblName + " WHERE algorithmId = ?;");
			ps.setString(1, algorithm_.getAlgorithmId());
			ps.executeUpdate();
			ps.close();

//			PreparedStatement ps1 = conn.prepareStatement("DELETE FROM Classification WHERE classificationId = ?;");
//			ps1.setString(1, algorithm_.getClassificationId());
//			ps1.executeUpdate();
//			ps1.close();

			return true;

		} catch (Exception e)
		{
			throw new Exception("Failed to delete algorithm: " + e.getMessage());
		}
	}

	private Algorithm generateAlgorithm(ResultSet resultSet) throws Exception
	{
		String algorithmName = resultSet.getString("algorithmName");
		String classificationId = resultSet.getString("classificationId");
		String algorithmId = resultSet.getString("algorithmId");
		return new Algorithm(algorithmName, classificationId, algorithmId);
	}
}
