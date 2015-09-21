package br.com.fiap.nac.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection getConnection() throws SQLException,
			ClassNotFoundException {
		// MYSQL

		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/mydb";
		String username = "root";
		String password = "root";

		// Load the JDBC driver
		Class.forName(driverName);
		// Create a connection to the database
		return DriverManager.getConnection(url, username, password);
	}

	// FECHA AS CONEXOES JAVA
	public static void closeConnection(Connection conn, PreparedStatement pstm,
			ResultSet rs) throws Exception {
		close(conn, pstm, rs);
	}

	public static void closeConnection(Connection conn, PreparedStatement pstm)
			throws Exception {
		close(conn, pstm, null);
	}

	public static void closeConnection(Connection conn) throws Exception {
		close(conn, null, null);
	}

	private static void close(Connection conn, PreparedStatement pstm,
			ResultSet rs) throws Exception {
		try {
			if (rs != null)
				rs.close();
			if (pstm != null)
				pstm.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
