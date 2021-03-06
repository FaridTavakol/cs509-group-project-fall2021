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
		String subClass = resultSet.getString("subClassification");
		return new Classification(id, name, superClass,subClass);
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
					"INSERT INTO Classification (classificationID, classificationName, superClassification, subClassification) values(?, ?, ?, ?);");
			ps.setString(1, id);
			ps.setString(2, classificationName);
			ps.setString(3, superClass);
			ps.setString(4, "");
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
				subClass += "," + classificationName;
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
	
	public boolean removeClassification(String classificationName) throws Exception{
		try {
			AlgorithmsDAO aDao = new AlgorithmsDAO();
			
			Classification c = getClassification(classificationName);
			
			if(!c.getSuperClassification().equals("")) {
				
				if(!deleteClassification(c)) return false;
				
				Classification superClass = getClassification(c.getSuperClassification());
				reclassifyChildren(c, superClass);
				
				//Reclassifies Algorithms
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM Algorithms WHERE classificationId=?;");
				ps.setString(1, c.getClassificationID());
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					String aID = rs.getString("algorithmId");
					aDao.reclassifyAlgorithm(aID, superClass.getClassificationID());
				}
				
				ps.close();
				rs.close();
				
				return true;
			}else return false;
		}catch(Exception e) {
			throw new Exception("Failed to remove classification: " + e.getMessage());
		}
	}
	
	public void reclassifyChildren(Classification c, Classification superClass) throws Exception{
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Classification WHERE classificationName=?;");
			ps.setString(1, c.getClassificationName());
			ResultSet resultSet = ps.executeQuery();
			
			String subClass = "";
			
			while (resultSet.next())
			{
				subClass = resultSet.getString("subClassification");
			}
			resultSet.close();
			ps.close();
			
			if(!subClass.equals("")) {
				String[] subC = subClass.split(",");
				subClass = "";
				
				for(String x: subC) {
					if(!x.equals(c.getClassificationName())) {
						PreparedStatement ps1 = conn.prepareStatement("UPDATE Classification SET superClassification= ? WHERE classificationName=?;");
						ps1.setString(1, superClass.getClassificationName());
						ps1.setString(2, x);
						int affected = ps1.executeUpdate();
						ps1.close();
						subClass += x + ",";
					}
				}
				subClass.substring(0, subClass.length() - 1);
			}
			
			PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM Classification WHERE classificationName=?;");
			ps2.setString(1, superClass.getClassificationName());
			ResultSet rs2 = ps2.executeQuery();
			
			String superSub = "";
			
			while (rs2.next())
			{
				superSub = rs2.getString("subClassification");
			}
			rs2.close();
			ps2.close();
			
			String superS[] = superSub.split(",");
			superSub = "";
			for(String x: superS) {
				if(!x.equals(c.getClassificationName())){
					superSub += x + ",";
				}
			}
			if(subClass.equals("")) superSub.substring(0, superSub.length() - 1);
			
			PreparedStatement ps1 = conn.prepareStatement("UPDATE Classification SET subClassification= ? WHERE classificationName=?;");
			ps1.setString(1, superSub + subClass);
			ps1.setString(2, superClass.getClassificationName());
			int affected = ps1.executeUpdate();
			ps1.close();

		}catch(Exception e) {
			throw new Exception("Cannot reclassify children: " + e.getMessage());
		}
	}

//	public boolean addClassification(Classification obj) throws Exception
//	{
//		// TODO Auto-generated method stub
//		try
//		{
//			Statement statement = conn.createStatement();
//			String query = "SELECT * FROM Classification ORDER BY classificationID DESC LIMIT 1";
//			ResultSet resultSet = statement.executeQuery(query);
//
//			String id = "";
//
//			while (resultSet.next())
//			{
//				Classification c = generateClassification(resultSet);
//				id = Integer.toString(Integer.parseInt(c.getClassificationID()) + 1);
//			}
//			resultSet.close();
//			statement.close();
//
//			PreparedStatement ps = conn.prepareStatement(
//					"INSERT INTO Classification (classificationID, classificationName, superClassification) values(?, ?, ?, ?);");
//			ps.setString(1, id);
//			ps.setString(2, obj.getClassificationName());
//			ps.setString(3, obj.getSuperClassification());
//			ps.setString(4, "");
//			ps.execute();
//			return true;
//
//		} catch (Exception e)
//		{
//			throw new Exception("Failed to add classification: " + e.getMessage());
//		}
//	}
	
	public List<Classification> getClassificationHeirarchy() throws Exception{
		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Classification;");
			ResultSet resultSet = ps.executeQuery();
			
			LinkedList<Classification> list = new LinkedList<Classification>();
			
			int i = 1;
			while (resultSet.next()) {
				Classification c = generateClassification(resultSet);
				if(!containsC(list, c)) {
					c.setH(Integer.toString(i));
					list.add(c);
					i++;
					list.addAll(generateHeirarchy(c.getH(), c));
				}
			}
			resultSet.close();
			ps.close();
			return list;
			
		}catch (Exception e) {
			throw new Exception("Failed to get classification heirarchy: " + e.getMessage());
		}
	}
	
	public boolean containsC(LinkedList<Classification> list, Classification c) {
		for(Classification x: list) {
			if(x.getClassificationID().equals(c.getClassificationID())) return true;
		}
		return false;
	}
	
	public LinkedList<Classification> generateHeirarchy(String h, Classification c) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Classification WHERE superClassification=?;");
			ps.setString(1, c.getClassificationName());
			ResultSet rs = ps.executeQuery();
			
			LinkedList<Classification> children = new LinkedList<Classification>();
			int i = 1;
			
			while(rs.next()) {
				Classification child = generateClassification(rs);
				child.setH(h + "." + i);
				children.add(child);
				children.addAll(generateHeirarchy(child.getH(), child));
				i++;
			}
			
			ps.close();
			rs.close();
			
			return children;
			
		}catch (Exception e) {
			throw new Exception("Failed to get children: " + e.getMessage());
		}	
	}
	
	public boolean mergeClassification(String one, String two) throws Exception{
		try {
			AlgorithmsDAO aDAO = new AlgorithmsDAO();
			
			Classification c1 = getClassification(one);
			Classification c2 = getClassification(two);
			
			if(c1.getSuperClassification().equals(c2.getSuperClassification()) && !c1.getSuperClassification().equals("") && !c2.getSuperClassification().equals("")) {
				reclassifyChildren(c2,getClassification(c2.getSuperClassification()));
				deleteClassification(c2);
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM Algorithms WHERE classificationId=?;");
				ps.setString(1, c2.getClassificationID());
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					String aID = rs.getString("algorithmId");
					aDAO.reclassifyAlgorithm(aID, c1.getClassificationID());
				}
				
				ps.close();
				rs.close();
				
				return true;
			}
			
		}catch (Exception e) {
			throw new Exception("Failed to merge classifications: " + e.getMessage());
		}
		return false;
	}

	
}