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

	public Algorithm getAlgorithm(String name) throws Exception
	{

		try
		{
			Algorithm algorithm = null;
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE algorithmName=?;");
			ps.setString(1, name);
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
			throw new Exception("Failed in getting Algorithm: " + e.getMessage());
		}
	}

	public Algorithm getAlgorithmByID(String algorithmID) throws Exception
	{

		try
		{
			Algorithm algorithm = null;
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE algorithmId=?;");
			ps.setString(1, algorithmID);
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
			throw new Exception("Failed in getting Algorithm: " + e.getMessage());
		}
	}

	public List<Algorithm> getAllAlgorithms() throws Exception
	{
		try
		{
			List<Algorithm> algorithms = new ArrayList<>();

			Statement statement = conn.createStatement();
			String query = "SELECT * FROM " + tblName + ";";
			ResultSet resultSet = statement.executeQuery(query);

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
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO " + tblName + " (algorithmName, classificationId, algorithmId) values(?, ?, ?);");
			ps.setString(1, algorithm_.getAlgorithmName());
			ps.setString(2, algorithm_.getClassificationId());
			ps.setString(3, algorithm_.getAlgorithmId());
			ps.execute();
			return true;

//			
//			ResultSet resultSet = ps.executeQuery();
//
//			// IF Algorithm already exists
//			while (resultSet.next())
//			{
//				Algorithm a = generateAlgorithm(resultSet);
//				resultSet.close();
//				return false;
//			}
//
//			ps = conn.prepareStatement(
//					"INSERT INTO " + tblName + " (algorithmName, classificationId, algorithmId) values(?,?,?);");
//			ps.setString(1, algorithm_.getAlgorithmName());
//			ps.setString(2, algorithm_.getClassificationId());
//			ps.setString(3, algorithm_.getAlgorithmId());
//			ps.execute();
//			return true;

		} catch (Exception e)
		{
			throw new Exception("Failed to add Algorithm: " + e.getMessage());
		}
	}

	public boolean deleteAlgorithm(String algorithmId_) throws Exception
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("DELETE FROM " + tblName + " WHERE algorithmId = ?;");
			ps.setString(1, algorithmId_);
			int numAffected = ps.executeUpdate();
			ps.close();
			return (numAffected == 1);

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

	public boolean reclassifyAlgorithm(String algorithmId_, String classificationId_) throws Exception
	{
		try
		{
			PreparedStatement ps = conn
					.prepareStatement("UPDATE " + tblName + " SET classificationId = ? WHERE algorithmId = ?;");
			ps.setString(1, classificationId_);
			ps.setString(2, algorithmId_);
			int numAffected = ps.executeUpdate();
			ps.close();
			return (numAffected == 1);

		} catch (Exception e)
		{
			throw new Exception("Failed to Reclassify algorithm(sql): " + e.getMessage());
		}
	}
}