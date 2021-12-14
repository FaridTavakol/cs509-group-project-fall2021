package edu.wpi.cs.proteus.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs.proteus.model.Log;
import edu.wpi.cs.proteus.model.PI;
import edu.wpi.cs.proteus.model.User;

public class LogDAO {
	java.sql.Connection conn;

	public LogDAO() {
		try {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			conn = null;
		}
	}

	private Log generateLog(ResultSet resultSet) throws Exception {
		int logID = resultSet.getInt("logID");
		String activityPerformedBy = resultSet.getString("activityPerformedBy");
		String activityPerformed = resultSet.getString("activityPerformed");
		String Date = resultSet.getString("Date");
		return new Log(logID,activityPerformedBy, activityPerformed,Date);
	}
	public boolean addLogEntry(Log object) throws Exception {
		try {
		
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO Log (activityPerformedBy,activityPerformed,Date) values(?, ?, ?);");
			ps.setString(1, object.getActivityPerformedBy());
			ps.setString(2, object.getActivityPerformed());
			ps.setString(3, object.getDate());
			ps.execute();
			return true;

		} catch (Exception e) {
			throw new Exception("Failed to add Log Entry: " + e.getMessage());
		}
	}
	
	public List<Log> getLog(String email) throws Exception {

		try {
			List<Log> logs = new ArrayList<>();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Log where activityPerformedBy=?;");
			ps.setString(1, email);
			ResultSet resultSet = ps.executeQuery();

			if (resultSet != null) {
				while (resultSet.next()) {
					Log l = generateLog(resultSet);
					logs.add(l);				
				}
				resultSet.close();
				ps.close();
				return logs;
			}

			else
				return null;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting User Activity: " + e.getMessage());
		}
	}


}
