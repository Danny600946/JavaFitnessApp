package javafx_exercise_app;

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

	private static TextField emailTextField = new TextField();

	public SignUpUI() {
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
		BorderPane.setAlignment(returnToLoginPageButton, Pos.CENTER);
		return returnToLoginPageButton;
	}

	/**
	 * Sets the CSS id's.
	 */
	private void setIds() {
		returnToLoginPageButton.setId("button");
	}

	/**
	 * Sets the CSS classes.
	 */
	private void setClasses() {
		registerButton.getStyleClass().add("button");

		registerLabel.getStyleClass().add("loginLabel");

		emailLabel.getStyleClass().add("loginSubLabel");

		emailTextField.getStyleClass().add("loginTextField");
	}

	/**
	 * Adds appropriate listeners to each node.
	 */
	private void addListeners() {
		returnToLoginPageButton.setOnAction(event -> {
			navBar.getChildren().remove(returnToLoginPageButton);
			navBar.getChildren().add(LoginUI.getSignUpButton());
			LoginUI.formatInputPane();
		});

		registerButton.setOnAction(event -> {
			RegistrationInfo newUser = new RegistrationInfo(usernameTextField.getText(), passwordField.getText(),
					emailTextField.getText());

			if (newUser.validateUsername().size() == 0 & newUser.validatePassword().size() == 0
					& newUser.validateEmail().size() == 0) {
				formatInputPane();
				inputPane.add(centerGridPaneNode(new Label("Registration Successful")), 0, 12);
			}

			else {
				inputPane.getChildren().clear();
				inputPane.add(centerGridPaneNode(registerLabel), 0, 0);
				inputPane.add(new Label(), 0, 1);
				inputPane.add(centerGridPaneNode(usernameLabel), 0, 2);
				inputPane.add(centerGridPaneNode(usernameTextField), 0, 3);
				usernameTextField.setPromptText("Enter Username");

				int i = 0;
				while (i < newUser.getUsernameMessageList().size()) {
					inputPane.add(centerGridPaneNode(new Label(newUser.getUsernameMessageList().get(i))), 0, 4 + i);
					System.out.println(i);
					i++;
				}

				inputPane.add(centerGridPaneNode(passwordLabel), 0, 5 + i);
				inputPane.add(centerGridPaneNode(passwordField), 0, 6 + i);
				passwordField.setPromptText("Enter Password");

				int j = 0;
				while (j < newUser.getPasswordMessageList().size()) {
					inputPane.add(centerGridPaneNode(new Label(newUser.getPasswordMessageList().get(j))), 0, 7 + i + j);
					j++;
				}

				inputPane.add(centerGridPaneNode(emailLabel), 0, 8 + i + j);
				inputPane.add(centerGridPaneNode(emailTextField), 0, 9 + i + j);
				emailTextField.setPromptText("Enter E-mail");

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

		inputPane.add(centerGridPaneNode(registerLabel), 0, 0);
		inputPane.add(new Label(), 0, 1);
		inputPane.add(centerGridPaneNode(usernameLabel), 0, 2);
		inputPane.add(centerGridPaneNode(usernameTextField), 0, 3);
		usernameTextField.setPromptText("Enter Username");
		inputPane.add(new Label(), 0, 4);
		inputPane.add(centerGridPaneNode(passwordLabel), 0, 5);
		inputPane.add(centerGridPaneNode(passwordField), 0, 6);
		passwordField.setPromptText("Enter Password");
		inputPane.add(new Label(), 0, 7);
		inputPane.add(centerGridPaneNode(emailLabel), 0, 8);
		inputPane.add(centerGridPaneNode(emailTextField), 0, 9);
		emailTextField.setPromptText("Enter E-mail");
		inputPane.add(new Label(), 0, 10);
		inputPane.add(centerGridPaneNode(registerButton), 0, 11);

		inputPane.setAlignment(Pos.CENTER);
	}

}
