package javafx_exercise_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {

	private static Connection conn = null;

	public static void connect() {

		try {
			// db parameters
			String url = "jdbc:sqlite:C:\\sqlite\\GetFit_db.db";
			// create a connection to the database
			conn = DriverManager.getConnection(url);

			System.out.println("Connection to SQLite has been established.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void closeConnection() {

		try {
			if (conn != null) {
				conn.close();
				System.out.println("Connection Terminated");
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());

		}

	}

	public static void insertNewUser(RegistrationInfo userDetails) throws SQLException {
		String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
		connect();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, userDetails.getUsername());
		statement.setString(2, userDetails.getPassword());
		statement.setString(3, userDetails.getEmail());

		try {
			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());

		} finally {
			statement.close();
			closeConnection();
		}
	}
}
