package javafx_exercise_app;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public abstract class StartUI {
	// Gets the dimensions of the users screen.
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	// Panes to contain nodes for the login and sign up pages.
	protected static BorderPane topPane = new BorderPane();
	protected static BorderPane navBar = new BorderPane();
	protected static GridPane inputPane = new GridPane();
	protected static Scene loginScene = new Scene(topPane);

	protected static Label usernameLabel = new Label("Username");
	protected static Label passwordLabel = new Label("Password");

	protected static TextField usernameTextField = new TextField();
	protected static PasswordField passwordField = new PasswordField();
	// Logo Image.
	protected static Image getFitImage = new Image("GetFitLogo.png");
	protected static ImageView logo = new ImageView(getFitImage);

	protected static Node centerGridPaneNode(Node node) {
		GridPane.setHalignment(node, HPos.CENTER);
		GridPane.setValignment(node, VPos.CENTER);
		return node;
	}

}
