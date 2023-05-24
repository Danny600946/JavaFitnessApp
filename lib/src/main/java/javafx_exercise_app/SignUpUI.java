package javafx_exercise_app;

import java.sql.SQLException;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class SignUpUI extends StartUI {
	private static Button returnToLoginPageButton = new Button("Login");
	private static Button registerButton = new Button("Register");

	private static Label registerLabel = new Label("Register");
	private static Label emailLabel = new Label("E-Mail");
	private static Label registrationSuccessLabel = new Label("Registration Successful");
	private static Label usernameTakenLabel = new Label("Username Taken");
	private static Label emailUsedLabel = new Label("Email Taken");

	private static TextField emailTextField = new TextField();

	public SignUpUI() {
		BorderPane.setAlignment(returnToLoginPageButton, Pos.CENTER);
		addListeners();
		setClasses();
		setIds();
	}

	/**
	 * Vertically centres the navBar login button and returns it.
	 * 
	 * @return returnToLoginPageButton The button to return to the login page
	 */
	public Button getCenteredReturnButton() {
		return returnToLoginPageButton;
	}

	/**
	 * Sets the CSS id's.
	 */
	private void setIds() {
		returnToLoginPageButton.setId("button");

		registrationSuccessLabel.setId("registrationSuccessLabel");
	}

	/**
	 * Sets the CSS classes.
	 */
	private void setClasses() {
		registerButton.getStyleClass().add("button");

		registerLabel.getStyleClass().add("loginLabel");

		emailLabel.getStyleClass().add("loginSubLabel");

		emailTextField.getStyleClass().add("loginTextField");

		usernameTakenLabel.getStyleClass().add("takenLabels");
		emailUsedLabel.getStyleClass().add("takenLabels");
	}

	/**
	 * Adds appropriate listeners to each node.
	 */
	private void addListeners() {
		// Listener for the login button on the registration page.
		returnToLoginPageButton.setOnAction(event -> {
			// Changes the navBar button to sign up.
			navBar.getChildren().remove(returnToLoginPageButton);
			navBar.getChildren().add(LoginUI.getSignUpButton());
			// Changes the inputPane to the login format.
			LoginUI.formatInputPane();
		});
		// Listener for the register button on the registration page.
		registerButton.setOnAction(event -> {
			// Creates a new registration object with the inputs.
			RegistrationInfo newUser = new RegistrationInfo(usernameTextField.getText(), passwordField.getText(),
					emailTextField.getText());
			// If all inputs are valid then the registration is accepted.
			if (newUser.validateUsername().size() == 0 & newUser.validatePassword().size() == 0
					& newUser.validateEmail().size() == 0) {
				// Re-formats the input pane.
				formatInputPane();
				// Attempts to add the new user to the database.
				try {
					Database.insertNewUser(newUser);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			/*
			 * If the inputs are not valid then the validation messages are displayed for
			 * the user to see below their respective labels. For example the username
			 * validation messages will appear below the username text field.
			 */
			else {
				// Empties the input pane.
				inputPane.getChildren().clear();
				inputPane.add(centerGridPaneNode(registerLabel), 0, 0);
				inputPane.add(new Label(), 0, 1);
				inputPane.add(centerGridPaneNode(usernameLabel), 0, 2);
				inputPane.add(centerGridPaneNode(usernameTextField), 0, 3);
				usernameTextField.setPromptText("Enter Username");
				// Loops through each username validation message and adds it to the input pane.
				int i = 0;
				while (i < newUser.getUsernameMessageList().size()) {
					inputPane.add(centerGridPaneNode(new Label(newUser.getUsernameMessageList().get(i))), 0, 4 + i);
					i++;
				}

				inputPane.add(centerGridPaneNode(passwordLabel), 0, 5 + i);
				inputPane.add(centerGridPaneNode(passwordField), 0, 6 + i);
				passwordField.setPromptText("Enter Password");
				// Loops through each password validation message and adds it to the input pane.
				int j = 0;
				while (j < newUser.getPasswordMessageList().size()) {
					inputPane.add(centerGridPaneNode(new Label(newUser.getPasswordMessageList().get(j))), 0, 7 + i + j);
					j++;
				}

				inputPane.add(centerGridPaneNode(emailLabel), 0, 8 + i + j);
				inputPane.add(centerGridPaneNode(emailTextField), 0, 9 + i + j);
				emailTextField.setPromptText("Enter E-mail");
				// Loops through each email validation message and adds it to the input pane.
				int h = 0;
				while (h < newUser.getEmailMessageList().size()) {
					inputPane.add(centerGridPaneNode(new Label(newUser.getEmailMessageList().get(h))), 0,
							10 + i + j + h);
					h++;
				}

				inputPane.add(new Label(), 0, 11 + i + j + h);
				inputPane.add(centerGridPaneNode(registerButton), 0, 12 + i + j + h);

			}

		});
	}

	/**
	 * Formats the are where the user inputs their details for registration.
	 */
	public static void formatInputPane() {
		inputPane.getChildren().clear();
		// Clears the text in the input fields.
		usernameTextField.clear();
		passwordField.clear();
		emailTextField.clear();
		// Adds login Label
		inputPane.add(centerGridPaneNode(registerLabel), 0, 0);
		inputPane.add(new Label(), 0, 1);
		// Adds username Label and TextField.
		inputPane.add(centerGridPaneNode(usernameLabel), 0, 2);
		inputPane.add(centerGridPaneNode(usernameTextField), 0, 3);
		usernameTextField.setPromptText("Enter Username");
		inputPane.add(new Label(), 0, 4);
		// Adds password Label and PasswordField.
		inputPane.add(centerGridPaneNode(passwordLabel), 0, 5);
		inputPane.add(centerGridPaneNode(passwordField), 0, 6);
		passwordField.setPromptText("Enter Password");
		inputPane.add(new Label(), 0, 7);
		// Adds email Label and TextField.
		inputPane.add(centerGridPaneNode(emailLabel), 0, 8);
		inputPane.add(centerGridPaneNode(emailTextField), 0, 9);
		emailTextField.setPromptText("Enter E-mail");
		inputPane.add(new Label(), 0, 10);
		// Adds registration button.
		inputPane.add(centerGridPaneNode(registerButton), 0, 11);
		// Centres the GridPane.
		inputPane.setAlignment(Pos.CENTER);
	}

	/*
	 * Method to be used in the Database Class to add the confirmation message if no
	 * exception is thrown when communicating with the db.
	 */
	public static void addRegistrationConfirmationMessage() {
		// Adds a confirmation message below register button.
		inputPane.add(centerGridPaneNode(registrationSuccessLabel), 0, 12);
	}

	public static void addUsernameTakenMessage(int rowNum) {
		// Adds a confirmation message below register button.
		inputPane.add(centerGridPaneNode(usernameTakenLabel), 0, rowNum);
	}

	public static void addEmailTakenMessage(int rowNum) {
		// Adds a confirmation message below register button.
		inputPane.add(centerGridPaneNode(emailUsedLabel), 0, rowNum);
	}

}
