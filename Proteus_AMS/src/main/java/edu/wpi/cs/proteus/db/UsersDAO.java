package edu.wpi.cs.proteus.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs.proteus.model.User;


/**
 * Note that CAPITALIZATION matters regarding the table name. If you create with 
 * a capital "Constants" then it must be "Constants" in the SQL queries.
 * 
 * @author heineman
 *
 */
public class UsersDAO { 

	java.sql.Connection conn;
	
	final String tblName = "User";   // Exact capitalization

    public UsersDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }

    public User getUser(String email) throws Exception {
        
        try {
        	User user = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE email=?;");
            ps.setString(1,  email);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                user = generateUser(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return user;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting constant: " + e.getMessage());
        }
    }

    private User generateUser(ResultSet resultSet) throws Exception {
        String email  = resultSet.getString("email");
        String password = resultSet.getString("password");
        String name = resultSet.getString("name");
        return new User (name,email, password);
    }

}