package edu.wpi.cs.proteus.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import edu.wpi.cs.proteus.model.Classification;

public class ClassificationDAO {
	
	java.sql.Connection conn;
	
	public ClassificationDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
	
	private Classification generateClassification(ResultSet resultSet) throws Exception {
		String id = resultSet.getString("classificationID");
		String name = resultSet.getString("classificationName");
		String superClass = resultSet.getString("superClassification");
		return new Classification(id, name, superClass);
	}
	
	public List<Classification> getAllClassifications() throws Exception {
    	try {
    		List<Classification> classifications = new ArrayList<>();
    		
    		Statement statement = conn.createStatement();
            String query = "SELECT * FROM Classification;";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Classification c = generateClassification(resultSet);
                classifications.add(c);
            }
            resultSet.close();
            statement.close();
    		
    		return classifications;
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new Exception("Failed in getting all classifications: " + e.getMessage());
    	}
    }
	
	public boolean addClassification(Classification classification) throws Exception {
		try {
			System.out.println("prepared statement-add classification");
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Classification (classificationID, classificationName, superClassification) values(?, ?, ?);");
            ps.setString(1,  classification.getClassificationID());
            ps.setString(2,  classification.getClassificationName());
            ps.setString(3,  classification.getSuperClassification());
            ps.execute();
            return true;

		} catch (Exception e) {
			throw new Exception("Failed to add classification: " + e.getMessage());
		}
	}
	
	public Classification getClassification(String classificationID) throws Exception {
    	try {
            Classification classification = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Classification WHERE classificationID=?;");
            ps.setString(1,  classificationID);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                classification = generateClassification(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return classification;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting classification: " + e.getMessage());
        }
    }
	
	public boolean deleteClassification(Classification classification) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Classification WHERE classificationID = ?;");
            ps.setString(1, classification.getClassificationID());
            int numAffected = ps.executeUpdate();
            ps.close();
            return (numAffected == 1);

        } catch (Exception e) {
            throw new Exception("Failed to delete classification: " + e.getMessage());
        }
    }

}
