package javafx_exercise_app;

import java.util.ArrayList;
import java.util.List;

public abstract class UserInfo {
	private String username;
	private String password;

	protected List<String> validationMessagesUsername = new ArrayList<>();
	protected List<String> validationMessagesPassword = new ArrayList<>();

	protected UserInfo(String username, String password) {
		this.username = username;
		this.password = password;
	}

	protected String getUsername() {
		return username;
	}

	protected String getPassword() {
		return password;
	}

}
