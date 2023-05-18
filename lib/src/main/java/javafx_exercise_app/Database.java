package javafx_exercise_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {

	private static Connection conn = null;

	/*
	 * Establishes a connection to the db if it can be found.
	 */
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

	/*
	 * Closes the connection to the db if it is open.
	 */
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

	/**
	 * 
	 * @param userDetails Object containing the users registration details.
	 * @throws SQLException Throws the exception if the db cannot be connected to
	 */
	public static void insertNewUser(RegistrationInfo userDetails) throws SQLException {
		connect();
		// Creates the SQL string and adds the necessary details/
		String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, userDetails.getUsername());
		statement.setString(2, userDetails.getPassword());
		statement.setString(3, userDetails.getEmail());

		try {
			statement.executeUpdate();
			// Displays message if no unique error is thrown.
			SignUpUI.addRegistrationConfirmationMessage();

		} catch (SQLException e) {
			System.out.println(e.getMessage());

		} finally {
			statement.close();
			// Closes connection to db.
			closeConnection();
		}
	}
}
