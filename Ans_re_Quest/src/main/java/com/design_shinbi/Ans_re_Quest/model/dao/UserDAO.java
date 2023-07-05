package com.design_shinbi.Ans_re_Quest.model.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.design_shinbi.Ans_re_Quest.model.Const;
import com.design_shinbi.Ans_re_Quest.model.entity.User;



public class UserDAO {
	protected Connection connection;
	
	public UserDAO(Connection connection) throws NoSuchAlgorithmException, SQLException
	{
		this.connection = connection;
		
		this.initialize();
	}
	
	public int count() throws SQLException {
		String sql = "SELECT COUNT(*) AS count FROM users";
		
		Statement statement = this.connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		int count = 0;
		if (resultSet.next()) {
			count = resultSet.getInt("count");
		}
		
		resultSet.close();
		statement.close();
		return count;
 	}
	
	public User findNew() throws SQLException {
		User user = null;
		
		String sql = "SELECT * FROM users ORDER BY id DESC LIMIT 1";
		
		java.sql.Statement statement = this.connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		if(resultSet.next()) {
			user = this.createUser(resultSet);
		}
		
		resultSet.close();
		statement.close();
		
		return user;
	}
	
	protected User createUser(ResultSet resultSet) throws SQLException {
		User user = new User();
		
		user.setId(resultSet.getInt("id"));
		user.setEmail(resultSet.getString("email"));
		user.setName(resultSet.getString("name"));
		user.setAdmin(resultSet.getBoolean("is_admin"));
		user.setPassword(resultSet.getString("password"));
		user.setCreatedAt(resultSet.getTimestamp("created_at"));
		user.setUpdatedAt(resultSet.getTimestamp("updated_at"));
		
		return user;
	}
	
	private static String createHash(String password)
	throws NoSuchAlgorithmException {
		MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
		String string = password;
		
		byte[] bytes = sha256.digest(string.getBytes());
		String hash = String.format(
				"%040x",
				new BigInteger(1, bytes));
		
		return hash;
	}
	
	public User addUser(String email, String name, String password, boolean isAdmin)
	throws SQLException, NoSuchAlgorithmException {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		
		String hashString = UserDAO.createHash(password);
		
		String sql = "INSERT INTO users (email, name, password, " + "is_admin,updated_at, created_at) "
		+ "VALUES(?, ?, ?, ?, ?, ?)";
		
		PreparedStatement statement = this.connection.prepareStatement(sql);
		statement.setString(1, email);
		statement.setString(2, name);
		statement.setString(3, hashString);
		statement.setBoolean(4, isAdmin);
		statement.setTimestamp(5,  now);
		statement.setTimestamp(6,  now);
		
		statement.executeUpdate();
		statement.close();
		
		User user = this.findNew();
		return user;
	}
	
	private void initialize() throws SQLException, NoSuchAlgorithmException {
		if (this.count() == 0)	{
			this.addUser(
					Const.DEFAULT_USER_EMAIL,
					Const.DEFAULT_USER_NAME,
					Const.DEFAULT_USER_PASSWORD,
					true);
		}
	}
	
	public User findByEmail(String email) throws SQLException {
		User user = null;
		
		String sql = "SELECT * FROM users WHERE email = ?";
		
		PreparedStatement statement = this.connection.prepareStatement(sql);
		statement.setString(1, email);
		
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			user = this.createUser(resultSet);
		}
		
		resultSet.close();
		statement.close();
		
		return user;
	}
	
	public User login(String email, String password)
	throws SQLException, NoSuchAlgorithmException {
		User user = this.findByEmail(email);
		
		if (user != null) {
			String hashString = UserDAO.createHash(password);
			if (!hashString.equals(user.getPassword())) {
				user = null;
			}
		}
		
		return user;
	}
	
	
	public List<User> findAll() throws SQLException {
		List<User> list = new ArrayList<User>();
		
		String sql = "SELECT * FROM users";
		
		Statement statement = this.connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while(resultSet.next()) {
			User user = this.createUser(resultSet);
			list.add(user);
		}
		
		resultSet.close();
		statement.close();
		
		return list;
	}
	
	public User findById(int id) throws SQLException {
		User user = null;
		
		String sql = "SELECT * FROM user WHELE id = ?";
		
		PreparedStatement statement = this.connection.prepareStatement(sql);
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()) {
			user = this.createUser(resultSet);
		}
		
		resultSet.close();
		statement.close();
		
		return user;
		
	}
	
	public void delete(int id) throws SQLException {
		String sql ="DELETE FROM users WHERE id = ?";
		
		PreparedStatement statement = this.connection.prepareStatement(sql);
		statement.setInt(1, id);
		statement.executeUpdate();
		statement.close();
	}

	
	public User updateUser(int id, String name, boolean isAdmin)
throws SQLException {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		
		String sql = "UPDATE users SET name = ?, is_admin = ?, "
				+ "updated_at = ? WHERE id = ?";
		
		PreparedStatement statement = this.connection.prepareStatement(sql);
		statement.setString(1, name);
		statement.setBoolean(2, isAdmin);
		statement.setTimestamp(3, now);
		statement.setInt(4, id);
		
		statement.executeUpdate();
		statement.close();
		
		User user = this.findById(id);
		return user;
	}
	
	public User updatePassword(int id, String password)
	throws SQLException, NoSuchAlgorithmException {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		
		User user = this.findById(id);
		String hash =UserDAO.createHash(password);
		
		
		String sql = "UPDATE users SET password = ?, update_at = ? WHERE id = ?";
		
		PreparedStatement statement = this.connection.prepareStatement(sql);
		statement.setString(1, hash);
		statement.setTimestamp(2, now);
		statement.setInt(3, id);
		
		statement.executeUpdate();
		statement.close();
		
		user = this.findById(id);
		return user;
	}
}
