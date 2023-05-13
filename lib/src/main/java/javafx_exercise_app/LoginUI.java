package javafx_exercise_app;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginUI extends StartUI {
	private SignUpUI signUpPage = new SignUpUI();
	// navBar sign up button.
	private static Button signUpButton = new Button("Sign Up");
	private static Button loginButton = new Button("Login");

	private static Label loginLabel = new Label("Login");

	public LoginUI(Stage primaryStage) {
		addListeners();
		setClasses();
		setIds();
		formatPanes();
		display(primaryStage);
	}

	/**
	 * Displays the main window primaryStage
	 * 
	 * @param primaryStage
	 */
	private void display(Stage primaryStage) {
		loginScene.getStylesheets().add("application.css");
		primaryStage.setScene(loginScene);
		primaryStage.setHeight(screenSize.height / 1.2);
		primaryStage.setWidth(screenSize.width / 3);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	/**
	 * Sets the CSS id's.
	 */
	private void setIds() {
		navBar.setId("navBar");
		inputPane.setId("inputPane");
	}

	/**
	 * Sets the CSS classes.
	 */
	private void setClasses() {
		signUpButton.getStyleClass().add("button");
		loginButton.getStyleClass().add("button");

		loginLabel.getStyleClass().add("loginLabel");

		usernameLabel.getStyleClass().add("loginSubLabel");
		passwordLabel.getStyleClass().add("loginSubLabel");

		usernameTextField.getStyleClass().add("loginTextField");
		passwordField.getStyleClass().add("loginTextField");
	}

	/**
	 * Adds listeners to nodes.
	 */
	private void addListeners() {
		signUpButton.setOnAction(event -> {
			navBar.getChildren().remove(signUpButton);
			navBar.setRight(signUpPage.getCenteredReturnButton());
			SignUpUI.formatInputPane();
		});
	}

	/**
	 * Formats the panes and the nodes for the login page.
	 * 
	 * @param signUpButton
	 */
	private void formatPanes() {
		formatNavBar();
		topPane.setTop(navBar);
		formatInputPane();
		topPane.setCenter(inputPane);
	}

	/**
	 * Formats the elements in the navBar.
	 * 
	 * @param button The button that appears in the navBar.
	 */
	private void formatNavBar() {
		// Sets image size. Preserving its aspect ratio.
		logo.setFitWidth(screenSize.width / 7);
		logo.setPreserveRatio(true);
		// Centres the button vertically.
		BorderPane.setAlignment(signUpButton, Pos.CENTER);
		navBar.setLeft(logo);
		navBar.setRight(signUpButton);
	}

	public static void formatInputPane() {
		inputPane.getChildren().clear();
		usernameTextField.clear();
		passwordField.clear();

		inputPane.add(centerGridPaneNode(loginLabel), 0, 0);
		inputPane.add(new Label(), 0, 1);
		inputPane.add(centerGridPaneNode(usernameLabel), 0, 2);
		inputPane.add(centerGridPaneNode(usernameTextField), 0, 3);
		usernameTextField.setPromptText("Enter Username");
		inputPane.add(new Label(), 0, 4);
		inputPane.add(centerGridPaneNode(passwordLabel), 0, 5);
		inputPane.add(centerGridPaneNode(passwordField), 0, 6);
		passwordField.setPromptText("Enter Password");
		inputPane.add(new Label(), 0, 7);
		inputPane.add(centerGridPaneNode(loginButton), 0, 8);

		inputPane.setAlignment(Pos.CENTER);
	}

	public static Button getSignUpButton() {
		return signUpButton;
	}

}
