package javafx_exercise_app;

import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Class to create and display the login and sign up UI.
 *
 * @version 1
 *
 * @author Daniel Vousden
 * 
 */

public class LoginUI {
	
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	// Container to hold the nav bar and login form.
	private BorderPane topContainer =  new BorderPane();
	// Nav bar for top of login.
	private AnchorPane navBar = new AnchorPane();
	// Form for login.
	private GridPane loginPane = new GridPane();
	private Scene loginPage = new Scene(topContainer);
	// Logo image.
	private Image logoImage = new Image("GetFitLogo.png");
	private ImageView getFitLogo = new ImageView(logoImage);
	// Labels for form inputs.
	private Label loginLabel = new Label("Login");
	private Label usernameLabel = new Label("Username");
	private Label passwordLabel = new Label("Password");
	// Form inputs for username and password.
	private TextField usernameInput = new TextField();
	private PasswordField passwordInput = new PasswordField(); 
	// Login and sign up buttons.
	private Button loginButton = new Button("Login");
	private Button signUpButton = new Button("Sign Up");
	
	/**
	 * Passes the mainWindow stage to the constructor to create and display the Login UI.
	 * 
	 * @param mainWindow The main stage being used to display the GUI.
	 */
	public LoginUI(Stage mainWindow) {
		loginPage.getStylesheets().add("application.css");
		
		centerGridChildNodes();
		setIds();
		setClasses();
		
		addNavBar();
		setLoginPane();
		
		display(mainWindow);
		
		// getHeight() only works after the scene is set.
		// Adjusts the position of the login form.
		loginPane.setPadding(new Insets(0, 0, loginPane.getHeight()/8, 0));
		// Centres the signup button vertically in the navBar pane.
		AnchorPane.setTopAnchor(signUpButton, (navBar.getHeight()-signUpButton.getHeight())/3);
	}
	
	/**
	 * Displays the main window.
	 * 
	 * @param mainWindow The main stage being used to display the GUI.
	 */
	private void display(Stage mainWindow) {
		// Sets height and width. Non Resizeable.
		mainWindow.setHeight(screenSize.getHeight()/1.5);
		mainWindow.setWidth(screenSize.getWidth()/2);
		mainWindow.setResizable(false);
		// Adds login scene and displays.
		mainWindow.setScene(loginPage);
		mainWindow.show();
	}
	
	/**
	 * setLoginPane() creates the login form for the main section of the scene.
	 */
	private void setLoginPane() {
		// Sets the centre of mainContainer as the login pane.
        topContainer.setCenter(loginPane);
		// Adds items to the grid layout.
	    loginPane.add(loginLabel, 0, 0);
	    // Used to add an empty row for formatting purposes.
	    loginPane.add(new Label(), 0, 1);
	    
	    loginPane.add(usernameLabel, 0, 2);
	    // Sets default input text before a user has input anything.
	    usernameInput.setPromptText("Enter your username");
	    loginPane.add(usernameInput, 0, 3);
	    // Used to add an empty row for formatting purposes.
	    loginPane.add(new Label(), 0, 4);
	    
	    loginPane.add(passwordLabel, 0, 5);
	    // Sets default input text before a user has input anything.
	    passwordInput.setPromptText("Enter your password");
	    loginPane.add(passwordInput, 0, 6);
	    // Used to add an empty row for formatting purposes.
	    loginPane.add(new Label(), 0, 7);
	    
	    loginPane.add(loginButton, 0, 8);
	    // Centres the gridLayout 
	    loginPane.setAlignment(Pos.CENTER);
	}
	
	/**
	 * addNavBar() adds the navBar pane to the top of the topContainer and adds its contents.
	 */
	private void addNavBar() {
		// Adds navBar pane to he top of the topContainer
		topContainer.setTop(navBar);
		// Sets width of button. Preserving the width to height ratio.
		getFitLogo.setFitWidth(200);
		getFitLogo.setPreserveRatio(true);
		// Adds the sign up button and logo to the navBar pane.
		navBar.getChildren().addAll(getFitLogo, signUpButton);
		// Used to position the button and logo image in the navBar.
		AnchorPane.setLeftAnchor(getFitLogo, 10.0);
		AnchorPane.setTopAnchor(getFitLogo, 10.0);
		AnchorPane.setBottomAnchor(getFitLogo, 10.0);
		
		AnchorPane.setRightAnchor(signUpButton, 10.0);
	}
	
	/**
	 * Calls a method on all nodes in the login grid pane.
	 */
	private void centerGridChildNodes() {
		centerNode(loginLabel);
		centerNode(usernameLabel);
		centerNode(passwordLabel);
		centerNode(loginButton);
	}
	
	/**
	 * Vertically and horizontally aligns each node in its respective grid cell.
	 * 
	 * @param node The node stored in the gridPane cell.
	 */
	private void centerNode(Node node) {
		GridPane.setHalignment(node, HPos.CENTER);
		GridPane.setValignment(node, VPos.CENTER);
	}
	
	/**
	 * Sets the ID's to be used with the JavaFX css styling.
	 */
	private void setIds() {
		loginPane.setId("loginPane");
		loginLabel.setId("loginLabel");
		loginButton.setId("loginButton");
		loginPane.setId("loginPane");
		signUpButton.setId(null);
		
		navBar.setId("navBar");
		getFitLogo.setId("logo");
	}
	
	/** 
	 * Sets the classes to be used with the JavaFX css styling.
	 */
	private void setClasses() {
		usernameLabel.getStyleClass().add("loginSubLabel");
		passwordLabel.getStyleClass().add("loginSubLabel");
		usernameInput.getStyleClass().add("loginTextField");
		passwordInput.getStyleClass().add("loginTextField");
		
		loginButton.getStyleClass().add("loginButton");
		signUpButton.getStyleClass().add("loginButton");
	}

}
