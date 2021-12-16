package edu.wpi.cs.proteus.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import edu.wpi.cs.proteus.model.Classification;

public class ClassificationDAO {

	java.sql.Connection conn;

	public ClassificationDAO()
	{
		try
		{
			conn = DatabaseUtil.connect();
		} catch (Exception e)
		{
			conn = null;
		}
	}

	private Classification generateClassification(ResultSet resultSet) throws Exception
	{
		String id = resultSet.getString("classificationID");
		String name = resultSet.getString("classificationName");
		String superClass = resultSet.getString("superClassification");		
		return new Classification(id, name, superClass);
	}

	public List<Classification> getAllClassifications() throws Exception
	{
		try
		{

			List<Classification> classifications = new ArrayList<>();

			Statement statement = conn.createStatement();
			String query = "SELECT * FROM Classification;";
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next())
			{
				Classification c = generateClassification(resultSet);
				classifications.add(c);
			}
			resultSet.close();
			statement.close();

			return classifications;
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception("Failed in getting all classifications: " + e.getMessage());
		}
	}

	public List<Classification> getAllChildClassifications() throws Exception
	{
		try
		{
			List<Classification> classifications = new ArrayList<>();

			Statement statement = conn.createStatement();
			String query = "SELECT * FROM Classification";// where classificationID IN (SELECT
															// DISTINCT(classificationId) from Algorithms)";
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next())
			{
				Classification c = generateClassification(resultSet);
				classifications.add(c);
			}
			resultSet.close();
			statement.close();

			return classifications;
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception("Failed in getting all classifications: " + e.getMessage());
		}
	}

	public boolean addClassification(String classificationName, String superClass) throws Exception
	{
		try
		{
			Statement statement = conn.createStatement();
			String query = "SELECT * FROM Classification ORDER BY classificationID DESC LIMIT 1";
			ResultSet resultSet = statement.executeQuery(query);

			String id = "";

			while (resultSet.next())
			{
				Classification c = generateClassification(resultSet);
				id = Integer.toString(Integer.parseInt(c.getClassificationID()) + 1);
			}
			resultSet.close();
			statement.close();

			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO Classification (classificationID, classificationName, superClassification) values(?, ?, ?);");
			ps.setString(1, id);
			ps.setString(2, classificationName);
			ps.setString(3, superClass);
			ps.execute();
			ps.close();
			
			if(addSubClass(classificationName, superClass)) return true;
			else return false;

		} catch (Exception e)
		{
			throw new Exception("Failed to add classification: " + e.getMessage());
		}
	}
	
	public boolean addSubClass(String classificationName, String superClass) throws Exception{
		try {
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Classification WHERE classificationName=?;");
			ps.setString(1, superClass);
			ResultSet resultSet = ps.executeQuery();
			
			String subClass = "";
			while (resultSet.next())
			{
				subClass = resultSet.getString("subClassification");
			}
			
			ps.close();
			resultSet.close();
			
			if(subClass != null) {
				subClass += ", " + classificationName;
			}else subClass = classificationName;
			
			PreparedStatement ps1 = conn.prepareStatement("UPDATE Classification SET subClassification= ? WHERE classificationName=?;");
			ps1.setString(1, subClass);
			ps1.setString(2, superClass);
			int affected = ps1.executeUpdate();
			
			if(affected > 0) return true;
			else return false;
			
		}catch(Exception e) {
			throw new Exception("Failed to add sub classification: " + e.getMessage());
		}
	}

	public Classification getClassification(String classificationName) throws Exception
	{
		try
		{

			Classification classification = null;
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Classification WHERE classificationName=?;");
			ps.setString(1, classificationName);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next())
			{
				classification = generateClassification(resultSet);
			}
			resultSet.close();
			ps.close();

			return classification;

		} catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception("Failed in getting classification: " + e.getMessage());
		}
	}
	
	public Classification getClassificationByID(String classificationID) throws Exception
	{
		try
		{

			Classification classification = null;
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Classification WHERE classificationID=?;");
			ps.setString(1, classificationID);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next())
			{
				classification = generateClassification(resultSet);
			}
			resultSet.close();
			ps.close();

			return classification;

		} catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception("Failed in getting classification: " + e.getMessage());
		}
	}

	public boolean deleteClassification(Classification classification) throws Exception
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("DELETE FROM Classification WHERE classificationID = ?;");
			ps.setString(1, classification.getClassificationID());
			int numAffected = ps.executeUpdate();
			ps.close();
			return (numAffected == 1);

		} catch (Exception e)
		{
			throw new Exception("Failed to delete classification: " + e.getMessage());
		}
	}

	public boolean addClassification(Classification obj) throws Exception
	{
		// TODO Auto-generated method stub
		try
		{
			Statement statement = conn.createStatement();
			String query = "SELECT * FROM Classification ORDER BY classificationID DESC LIMIT 1";
			ResultSet resultSet = statement.executeQuery(query);

			String id = "";

			while (resultSet.next())
			{
				Classification c = generateClassification(resultSet);
				id = Integer.toString(Integer.parseInt(c.getClassificationID()) + 1);
			}
			resultSet.close();
			statement.close();

			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO Classification (classificationID, classificationName, superClassification) values(?, ?, ?);");
			ps.setString(1, id);
			ps.setString(2, obj.getClassificationName());
			ps.setString(3, obj.getSuperClassification());
			ps.execute();
			return true;

		} catch (Exception e)
		{
			throw new Exception("Failed to add classification: " + e.getMessage());
		}
	}
	
	public List<Classification> getClassificationHeirarchy() throws Exception{
		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Classification WHERE superClassification=?;");
			ps.setString(1, "");
			ResultSet resultSet = ps.executeQuery();
			
			LinkedList<Classification> list = new LinkedList<Classification>();
			
			while (resultSet.next()) {
				Classification c = generateHeirarchy(resultSet);
				list.add(c);
			}
			resultSet.close();
			ps.close();
			return list;
			
		}catch (Exception e) {
			throw new Exception("Failed to get classification heirarchy: " + e.getMessage());
		}
	}
	
	public Classification generateHeirarchy(ResultSet resultSet) throws Exception {
		try {
			String id = resultSet.getString("classificationID");
			String name = resultSet.getString("classificationName");
			String superClass = resultSet.getString("superClassification");		
			LinkedList<Classification> subClasses = new LinkedList<Classification>();
			
			String subClass = resultSet.getString("subClassification");
			String sub[];
			if(subClass != null) {
				sub = subClass.split(",");
				for(String n: sub) {
					PreparedStatement ps = conn.prepareStatement("SELECT * FROM Classification WHERE classificationName=?;");
					ps.setString(1, n);
					ResultSet rs = ps.executeQuery();
					
					while(rs.next()) {
						subClasses.add(generateHeirarchy(rs));
					}
					
					rs.close();
					ps.close();
				}
			}
			
			Classification h = new Classification(id, name, superClass, subClasses);
			
			return h;
			
		}catch (Exception e) {
			throw new Exception("Failed to get classification heirarchy: " + e.getMessage());
		}	
	}
	
	public boolean mergeClassification(String one, String two) throws Exception{
		try {
			Classification c1 = getClassification(one);
			Classification c2 = getClassification(two);
			
			if(deleteClassification(c2)) {
				//reclassify all algorithms with classification two id
			}
			
		}catch (Exception e) {
			throw new Exception("Failed to merge classifications: " + e.getMessage());
		}
		return false;
	}

	
}
