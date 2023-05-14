package javafx_exercise_app;

import java.util.ArrayList;
import java.util.List;

public class RegistrationInfo extends UserInfo {

	private String email;

	protected List<String> validationMessagesEmail = new ArrayList<>();

	protected RegistrationInfo(String username, String password, String email) {
		super(username, password);
		this.email = email;
	}

	public List<String> validateUsername() {
		validationMessagesUsername.clear();

		if (getUsername().length() < 6) {
			validationMessagesUsername.add("Must have more than 5 characters.");
		}

		if (getUsername().matches(".*[@#$%^&+= ].*")) {
			validationMessagesUsername.add("No special characters or spaces.");
		}

		return validationMessagesUsername;

	}

	public List<String> validatePassword() {
		validationMessagesPassword.clear();
		boolean containsACapitalLetter;

		if (getPassword().equals("")) {
			validationMessagesPassword.add("Should not contain spaces.");
		}

		if (getPassword().contains(" ")) {
			validationMessagesPassword.add("Should not contain spaces.");
		}

		if (!getPassword().matches(".*[1234567890].*")) {
			validationMessagesPassword.add("At least one number.");
		}

		containsACapitalLetter = false;
		for (int i = 0; i < 26; i++) {
			if (getPassword().contains(Character.toString((char) (65 + i)))) {
				containsACapitalLetter = true;
			}
		}

		if (!containsACapitalLetter) {
			validationMessagesPassword.add("At least one capital letter.");
		}

		return validationMessagesPassword;

	}

	public List<String> validateEmail() {
		validationMessagesEmail.clear();
		boolean invalidEmail = false;
		int indexOfAtInEmail = 0;
		int indexOfLastPeriod = 0;
		int numberOfAtsInEmail = 0;
		int numberOfPeriods = 0;

		if (email.equals("")) {
			invalidEmail = true;
		}

		if (email.matches(".*[#$%^&+= ].*")) {
			invalidEmail = true;
		}

		for (int i = 0; i < email.length(); i++) {

			if ((email.charAt(i) == '-') && (email.charAt(i + 1) == '-')) {
				invalidEmail = true;
			}

			if ((email.charAt(i) == '_') && (email.charAt(i + 1) == '_')) {
				invalidEmail = true;
			}

			if ((email.charAt(i) == '.') && (email.charAt(i + 1) == '.')) {
				invalidEmail = true;
			}

			if (((email.charAt(i) == '@') && (i != 0))) {
				indexOfAtInEmail = i;
				numberOfAtsInEmail++;
			}

			if (email.charAt(i) == '.') {
				indexOfLastPeriod = i;
				numberOfPeriods++;
			}

		}

		if (numberOfAtsInEmail != 1 || email.charAt(0) == '.' || email.charAt(0) == '-' || email.charAt(0) == '_'
				|| indexOfLastPeriod < indexOfAtInEmail || numberOfPeriods == 0
				|| email.charAt(email.length() - 1) == '.' || email.charAt(email.length() - 1) == '-'
				|| email.charAt(email.length() - 1) == '_') {
			invalidEmail = true;
		}

		if (invalidEmail == true) {
			validationMessagesEmail.add("Please enter a valid email.");
		}

		return validationMessagesEmail;
	}

	public List<String> getUsernameMessageList() {
		return validationMessagesUsername;
	}

	public List<String> getPasswordMessageList() {
		return validationMessagesPassword;
	}

	public List<String> getEmailMessageList() {
		return validationMessagesEmail;
	}

}
