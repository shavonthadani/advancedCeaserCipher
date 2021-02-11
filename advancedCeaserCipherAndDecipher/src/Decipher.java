

import java.util.Scanner;

public class Decipher {
	public static String decipheredMessage = "";
	public static String encryptedMessage = "";
	private static Scanner input = new Scanner(System.in);

	/**
	 * This method asks the user for their message that they want to be deciphered.
	 *
	 * Example:
	 * 
	 * if user enters "ASD FET ASD F" then userInput = "ASD FET ASD F".
	 * 
	 * @return returns the variable encryptedMessage which stores the user's input
	 */
	private static String encryptedMessage() {
		System.out.println("enter your encrytped message");
		encryptedMessage = input.nextLine();
		if (encryptedMessage.equals("")) {
			System.out.println("Did not enter anything. Please enter your message");
			encryptedMessage();
		}
		return encryptedMessage;
	}

	/**
	 * this method removes all spaces and punctuation from the encrypted message
	 * Example: if user enters "ASD FET, ASD F!" then this method will output
	 * "ASDFETASDF"
	 * 
	 * @return returns encryptedMessage after it has been removed of punctuation and
	 *         spaces
	 */
	private static String removeSpaces() {
		encryptedMessage = encryptedMessage.replaceAll("\\p{Punct}", "");
		encryptedMessage = encryptedMessage.replaceAll("x", "");
		encryptedMessage = (encryptedMessage.replaceAll(" ", "")).toUpperCase();
		if (encryptedMessage.equals("")) {
			System.out.println("you did not enter valid characters. Bye");
			System.exit(0);
		}
		return encryptedMessage;
	}

	/**
	 * shifts alphabet opposite to the encrypt class to get deciphered message
	 * Example: encrypted message = "BCD" and key = 1 then deciphered message =
	 * "ABC"
	 * 
	 * @param alphabet is a string contains every letter and allows user to type the
	 *                 alphabet of any language and will shift the letters
	 *                 accordingly
	 * @param key      is an integer that the user chooses and will shift the
	 *                 letters by the opposite that number
	 * @return returns the variable decipheredMessage which is the final deciphered
	 *         message.
	 */
	private static String shifter(String alphabet, int key) {
		String letter = "";
		if (key > alphabet.length()) {
			while (key > alphabet.length()) {
				key = key - alphabet.length();
			}
		}
		int position = 0;
		char alphaChar;
		for (int i = 0; i < encryptedMessage.length(); i++) {
			char ch = encryptedMessage.charAt(i);
			letter = Character.toString(ch);
			if (alphabet.toUpperCase().contains(letter)) {
				position = alphabet.toUpperCase().indexOf(ch) - key;
				if (position < 0) {
					position = position + alphabet.length();
				}
				if (position >= alphabet.length()) {
					position = position - alphabet.length();
				}
				alphaChar = alphabet.toUpperCase().charAt(position);
				decipheredMessage = decipheredMessage + alphaChar;
				decipheredMessage = decipheredMessage.replace("null", "");
			} else {
				System.out.println("characters are not in the alphabet. Bye");
				input.close();
				System.exit(0);
			}

		}
		return decipheredMessage;
	}

	public static void main(String[] args) {
		int num = 1;
		// test cases
		// num = 1;
		// num = -2;
		// num = 52;
		// enter letter that will loop around
		// enter no message
		// enter a something not in alphabet
		encryptedMessage();
		removeSpaces();
		System.out.println("your deciphered message is: " + shifter("abcdefghijklmnopqrstuvwxyz", num));
	}

}
