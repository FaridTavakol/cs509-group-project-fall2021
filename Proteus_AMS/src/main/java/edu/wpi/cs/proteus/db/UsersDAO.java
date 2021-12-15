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

	public java.sql.Connection conn;

	final String tblName = "User"; // Exact capitalization

	public UsersDAO() {
		try {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			conn = null;
		}
	}

	public User getUser(String email) throws Exception {

		try {
			User user = null;
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE email=?;");
			ps.setString(1, email);
			ResultSet resultSet = ps.executeQuery();

			if (resultSet != null) {
				while (resultSet.next()) {
					user = generateUser(resultSet);
				}
				resultSet.close();
				ps.close();
				return user;
			}

			else
				return null;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting User: " + e.getMessage());
		}
	}

	public User getUserCredentials(String email, String password) throws Exception {

		try {
			User user = null;
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE email=? and password=?;");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet resultSet = ps.executeQuery();

			if (resultSet != null) {
				while (resultSet.next()) {
					user = generateUser(resultSet);
				}
				resultSet.close();
				ps.close();
				return user;
			}

			else
				return null;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting User: " + e.getMessage());
		}
	}

	public boolean addUser(User newUser) throws Exception {
		try {
			PreparedStatement ps = conn
					.prepareStatement("INSERT INTO " + tblName + " (Name,Email,Password,Role) values(?,?,?,?);");
			ps.setString(1, newUser.getName());
			ps.setString(2, newUser.getEmail());
			ps.setString(3, newUser.getPassword());
			ps.setString(4, newUser.getRole());

			ps.execute();
			return true;

		} catch (Exception e) {
			throw new Exception("Failed to insert constant: " + e.getMessage());
		}
	}
	public boolean deleteUser(String email) throws Exception {
		try {
			PreparedStatement ps = conn
					.prepareStatement("DELETE FROM " + tblName + "  WHERE EMAIL = ?;");
			ps.setString(1, email);
			ps.execute();
			return true;

		} catch (Exception e) {
			throw new Exception("Failed to Delete User: " + e.getMessage());
		}
	}
	private User generateUser(ResultSet resultSet) throws Exception {
		String email = resultSet.getString("email");
		String password = resultSet.getString("password");
		String name = resultSet.getString("name");
		String role = resultSet.getString("Role");
		return new User(name, email, password, role);
	}

	public List<User> getAllUser() throws Exception {

		try {
			List<User> users = new ArrayList<>();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM User WHERE Role='Register';");
			ResultSet resultSet = ps.executeQuery();

			if (resultSet != null) {
				while (resultSet.next()) {
					User u = generateUser(resultSet);
					users.add(u);				
				}
				resultSet.close();
				ps.close();
				return users;
			}

			else
				return null;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting All User: " + e.getMessage());
		}
	}

}
