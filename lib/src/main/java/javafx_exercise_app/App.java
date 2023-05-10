package javafx_exercise_app;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application{
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

	@Override
	public void start(Stage mainWindow) throws Exception {
		// TODO Auto-generated method stub
		displayLoginPage(mainWindow);
		
		
	}
	
	private void displayLoginPage(Stage mainWindow) {
		// Creates and displays a new login window
		LoginUI login = new LoginUI(mainWindow);
		
	}


}
