package javafx_exercise_app;

import java.util.ArrayList;
import java.util.List;

public class RegistrationInfo extends UserInfo {

	private String email;

	protected List<String> validationMessagesEmail = new ArrayList<>();

	// Creates an object to store the registration information.
	protected RegistrationInfo(String username, String password, String email) {
		super(username, password);
		this.email = email;
	}

	/**
	 * This method is to check if the user name entered by the user is valid.
	 * 
	 * @return validationMessagesUsername Returns a list of Strings which describe
	 *         the validation issues with the user name if there are any.
	 */
	public List<String> validateUsername() {
		// Checks the length of the user name is more than 5 characters.
		if (getUsername().length() < 6) {
			validationMessagesUsername.add("Must have more than 5 characters.");
		}
		// Checks that username does not contain any illegal characters.
		if (getUsername().matches(".*[@#$%^&+= ].*")) {
			validationMessagesUsername.add("No special characters or spaces.");
		}

		return validationMessagesUsername;

	}

	/**
	 * This method is to check if the password entered by the user is valid.
	 * 
	 * @return validationMessagesPassword Returns a list of Strings which describe
	 *         the validation issues with the password if there are any.
	 */
	public List<String> validatePassword() {
		boolean containsACapitalLetter;
		// Input is empty.
		if (getPassword().equals("")) {
			validationMessagesPassword.add("Should not contain spaces.");
		}
		// Input contains a space.
		if (getPassword().contains(" ")) {
			validationMessagesPassword.add("Should not contain spaces.");
		}
		// Input doesn't contain a number.
		if (!getPassword().matches(".*[1234567890].*")) {
			validationMessagesPassword.add("At least one number.");
		}

		containsACapitalLetter = false;
		// Checks each letter to see if the input contains a capital letter.
		for (int i = 0; i < 26; i++) {
			if (getPassword().contains(Character.toString((char) (65 + i)))) {
				containsACapitalLetter = true;
			}
		}
		// Doesn't contain a capital then the validation message is added.
		if (!containsACapitalLetter) {
			validationMessagesPassword.add("At least one capital letter.");
		}

		return validationMessagesPassword;

	}

	/**
	 * Validates the users email address against requirements.
	 * 
	 * @return validationMessagesEmail List of the error messages.
	 */
	public List<String> validateEmail() {
		boolean invalidEmail = false;
		// Values to track the index and amount of certain characters in the email.
		int indexOfAtInEmail = 0;
		int indexOfLastPeriod = 0;
		int numberOfAtsInEmail = 0;
		int numberOfPeriods = 0;
		// No input.
		if (email.equals("")) {
			invalidEmail = true;
		}
		// Input contains illegal characters.
		if (email.matches(".*[#$%^&+= ].*")) {
			invalidEmail = true;
		}
		// Iterates through each character in the email if it is not already an invalid
		// email.
		for (int i = 0; i < email.length() && invalidEmail == false; i++) {
			// Makes sure it doesn't cause an out of bounds error.
			if (i + 1 < email.length()) {
				// Checks for consecutive - characters.
				if ((email.charAt(i) == '-') && (email.charAt(i + 1) == '-')) {
					invalidEmail = true;
				}
				// Checks for consecutive _ characters.
				if ((email.charAt(i) == '_') && (email.charAt(i + 1) == '_')) {
					invalidEmail = true;
				}
				// Checks for consecutive . characters.
				if ((email.charAt(i) == '.') && (email.charAt(i + 1) == '.')) {
					invalidEmail = true;
				}
			}
			// Store the index of the last @ and the number of @'s.
			if (((email.charAt(i) == '@') && (i != 0))) {
				indexOfAtInEmail = i;
				numberOfAtsInEmail++;
			}
			// Store the index of the last . and the number of periods.
			if (email.charAt(i) == '.') {
				indexOfLastPeriod = i;
				numberOfPeriods++;
			}

		}
		/*
		 * Checks The number of @'s in the email are 1, The first character isn't a . ,
		 * The first character isn't a -, The first character isn't an _, The last
		 * character isn't a ., The last character isn't a -, The last character isn't
		 * an _.
		 */
		if (numberOfAtsInEmail != 1 || email.charAt(0) == '.' || email.charAt(0) == '-' || email.charAt(0) == '_'
				|| indexOfLastPeriod < indexOfAtInEmail || numberOfPeriods == 0
				|| email.charAt(email.length() - 1) == '.' || email.charAt(email.length() - 1) == '-'
				|| email.charAt(email.length() - 1) == '_') {
			invalidEmail = true;
		}
		// If the email is invalid the validation message is added to the list.
		if (invalidEmail == true) {
			validationMessagesEmail.add("Please enter a valid email.");
		}

		return validationMessagesEmail;
	}

	/**
	 * Get method for the user name validation messages.
	 * 
	 * @return validationMessagesUsername The list of Validation messages for the
	 *         user name.
	 */
	public List<String> getUsernameMessageList() {
		return validationMessagesUsername;
	}

	/**
	 * Get method for the password validation messages.
	 * 
	 * @return validationMessagesPassword The list of Validation messages for the
	 *         password.
	 */
	public List<String> getPasswordMessageList() {
		return validationMessagesPassword;
	}

	/**
	 * Get method for the email validation messages.
	 * 
	 * @return validationMessagesEmail The list of Validation messages for the
	 *         email.
	 */
	public List<String> getEmailMessageList() {
		return validationMessagesEmail;
	}

	/**
	 * Get method for the registration email.
	 * 
	 * @return email The users email in String format.
	 */
	protected String getEmail() {
		return email;
	}

}
