package edu.wpi.cs.proteus.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs.proteus.model.PI;


public class PIDAO {

	
	java.sql.Connection conn;

	public PIDAO() {
		try {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			conn = null;
		}
	}

	private PI generateProblemInstance(ResultSet resultSet) throws Exception {
		String ID = String.valueOf(resultSet.getInt("problemInstanceID"));
		String algorithmID = resultSet.getString("algorithmID");
		String name = resultSet.getString("Name");
		String description = resultSet.getString("Description");
		String url = resultSet.getString("URL");
		String content = resultSet.getString("File");
		
		return new PI(ID,algorithmID,name,description,url,content,"");
	}
	public boolean addPI(PI object) throws Exception {
		try {
		
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO ProblemInstance (Name,algorithmID,Description,URL,File) values(?, ?, ?, ?, ?);");
			ps.setString(1, object.getName());
			ps.setString(2, object.getAlgorithmID());
			ps.setString(3, object.getDescription());
			ps.setString(4, object.getURL());
			ps.setString(5, object.getFileContent());
			ps.execute();
			return true;

		} catch (Exception e) {
			throw new Exception("Failed to add Problem Instance: " + e.getMessage());
		}
	}
	
	public boolean deletePI(int PI_id) throws Exception {
		try {
		
			PreparedStatement ps = conn.prepareStatement(
					"DELETE FROM ProblemInstance WHERE problemInstanceID = ?;");
			ps.setInt(1, PI_id);
			ps.execute();
			return true;

		} catch (Exception e) {
			throw new Exception("Failed to delete Problem Instance: " + e.getMessage());
		}
	}
	

	public List<PI> getPI(String ID) throws Exception {
		try {
			List<PI> pinstances = new ArrayList<>();

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ProblemInstance WHERE algorithmID=?;");
			ps.setString(1, ID);
			ResultSet resultSet = ps.executeQuery();
			
			while (resultSet.next()) {
				PI p = generateProblemInstance(resultSet);
				pinstances.add(p);
			}
			resultSet.close();
			ps.close();

			return pinstances;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting Problem Instances: " + e.getMessage());
		}
	}


}
