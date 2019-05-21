package com.chainsys.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionUtil {
	public static Connection getConnection() throws Exception  {
		Connection connection=null;
		try
		{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		connection= DriverManager.getConnection(url, "hr", "hr");
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Unable to connect to database");
		}
		return connection;
	}

	public static void closeConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet)
			throws SQLException {
		if (connection != null) {
			connection.close();
		}
		if (preparedStatement != null) {
			preparedStatement.close();
		}
		if (resultSet != null) {
			resultSet.close();
		}
	}
}
