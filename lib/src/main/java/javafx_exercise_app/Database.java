package javafx_exercise_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	 * Inserts the new user info into the db if there is no UNIQUE constraint
	 * violations (Duplication of data that is suppose to be UNIQUE).
	 * 
	 * @param userDetails Object containing the users registration details.
	 * @throws SQLException Throws the exception if the db cannot be connected to
	 */
	public static void insertNewUser(RegistrationInfo userDetails) throws SQLException {
		connect();
		// Creates the SQL string and adds the necessary details.
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
			String typeOfConstraintViolation = e.getMessage();

			if (typeOfConstraintViolation.contains("users.username")) {
				System.out.println("Username Already Exists");
				// Display the error message to the user.
				SignUpUI.addUsernameTakenMessage(12);
			}

			if (typeOfConstraintViolation.contains("users.email")) {
				// Display the error message to the user.
				SignUpUI.addEmailTakenMessage(12);
				String username = userDetails.getUsername();
				System.out.println(username);
				if (hasMatchInColumn(username)) {
					System.out.println("Yo");
					// Display the error message to the user.
					SignUpUI.addUsernameTakenMessage(13);
				}

			}

		} finally {
			statement.close();
			// Closes connection to db.
			closeConnection();
		}
	}

	/**
	 * These methods retrieves the username column from the database.
	 * 
	 * @return allUsernamesInDatabase An object that provides access to the user
	 *         name column.
	 * @throws SQLException Thrown if statement cannot be created correctly.
	 */
	private static ResultSet getUsernameColumn() throws SQLException {
		String usernameColQuery = "SELECT username FROM users";
		Statement statement = conn.createStatement();
		ResultSet allUsernamesInDatabase = null;

		try {
			allUsernamesInDatabase = statement.executeQuery(usernameColQuery);
		} catch (SQLException e) {
			System.out.println("Query could  not be executed");
		} finally {
			statement.close();
		}

		return allUsernamesInDatabase;

	}

	/**
	 * Checks if the registration username is already in the db.
	 * 
	 * @param username The username being searched for.
	 * @return matchFound Signifying if the username is already present in the db.
	 * @throws SQLException Thrown if getUsernameColumn(); throws.
	 */
	private static boolean hasMatchInColumn(String username) throws SQLException {
		// Gets the RuleSet representing the username column.
		ResultSet usernamesInDatabase = getUsernameColumn();
		boolean matchFound = false;
		System.out.println(usernamesInDatabase.next());
		// Loops until a match is found or there are no more usernames to check.
		while (!matchFound && usernamesInDatabase.next()) {
			String value = usernamesInDatabase.getString("username");

			if (username.equals(value)) {
				matchFound = true;
			}
		}
		return matchFound;
	}

}
