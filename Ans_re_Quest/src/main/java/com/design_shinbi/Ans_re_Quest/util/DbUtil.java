package com.design_shinbi.Ans_re_Quest.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	private static final String DB_DRIVER = "jdbc:mysql://localhost/ans_re_quest";
	private static final String DB_USER = "ans";
	private static final String DB_PASSWORD ="ans";
	
	public static Connection connect() throws ClassNotFoundException,SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection(
				DB_DRIVER,
				DB_USER,
				DB_PASSWORD
		);
		return connection;
	}
}
