package eu.ubis.fiimdb.db.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
	private static Connection connection;
	private static final String URL = "jdbc:mysql://localhost/fiimdb";
	private static final String USERNAME = "fiimdb";
	private static final String PASSWORD = "fiimdb";

	private ConnectionHelper() {
	}

	public static Connection getConnection() {

		if (connection == null) {
			try {
				System.out.println("LOADING DRIVER...");

				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("DRIVER LOADED...");
				System.out.println("INITIALIZING CONNECTION...");
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				System.out.println("CONNECTION INITIALIZED!");
			} catch (ClassNotFoundException e) {
				throw new IllegalStateException("Cannot find the driver in the classpath!", e);
			} catch (SQLException e) {
				throw new IllegalStateException("Cannot connect the database!", e);
			}
		}

		return connection;
	}
}
