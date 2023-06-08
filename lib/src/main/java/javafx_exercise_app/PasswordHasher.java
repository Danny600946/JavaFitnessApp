package javafx_exercise_app;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public abstract class PasswordHasher {

	/**
	 * This method is used to hash a password ready to be stored in the database.
	 * 
	 * @param password The password in string form to be hashed.
	 * @return hash The hashed password.
	 * @throws NoSuchAlgorithmException Thrown if PBKDF2WithHmacSHA1 isn't a valid
	 *                                  algorithm.
	 * @throws InvalidKeySpecException  Thrown if the KeySpec isn't valid.
	 */
	public static byte[] hashPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		// Used to generate random values securely.
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		// Fills the byte array with random values.
		random.nextBytes(salt);
		// Defines the specs of the hash process.
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);

		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hash = factory.generateSecret(spec).getEncoded();

		byte[] combinedHash = new byte[salt.length + hash.length];
		// Copies the salt and hashed password into one byte array.
		System.arraycopy(salt, 0, combinedHash, 0, salt.length);
		System.arraycopy(hash, 0, combinedHash, salt.length, hash.length);

		return combinedHash;

	}

}
