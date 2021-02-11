

import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class Crypto {
	private static Scanner input = new Scanner(System.in);
	public static String usersInput;
	public static String alphaShifter;
	
	private static String read(String FileName) throws FileNotFoundException{
		String test = "";
		new File (FileName);
		while(input.hasNext()) {
			test +=input.nextLine()+"\n";
		}
		input.close();
		return test;
		
	}

	/**
	 * This method asks the user for their message that they want to be encrypted.
	 *
	 * Example:
	 *
	 * if user enters "hello world" then userInput = "hello world".
	 * 
	 * @return returns the variable usersInput which stores the user's input
	 */
	private static String usersInput() {
		System.out.println("please enter your message");
		usersInput = input.nextLine();
		if (usersInput.equals("")) {
			System.out.println("Did not enter anything. Please enter your message");
			usersInput();
		}
		return usersInput;
	}

	/**
	 * method deletes punctuation and spaces and makes it all capitalized Example:
	 * user enters "Hello, World!" output is HELLOWORLD
	 * 
	 * @return returns usersInput after removing punctuation and spaces
	 */
	 static String removePunctuationAndSpaces() {
		usersInput = usersInput.replaceAll("\\p{Punct}", "");
		usersInput = (usersInput.replaceAll(" ", "")).toUpperCase();
		if (usersInput.equals("")) {
			System.out.println("Did not valid characters. Bye");
			input.close();
			System.exit(0);
		}
		return usersInput;
	}

	/**
	 * method shifts each letter by the key Example: key = 1 and usersInput = "ABC"
	 * output is "BCD".
	 * 
	 * @param alphabet is a string contains every letter and allows user to type the
	 *                 alphabet of any language and will shift the letters
	 *                 accordingly
	 * @param key      is an integer that the user chooses and will shift the
	 *                 letters by that number
	 * @return returns the variable alphaShifter which is the variable containing
	 *         the the shifted message
	 */
	private static String alphabetShifter(String alphabet, int key) {
		if (key > alphabet.length()) {
			while (key > alphabet.length()) {
				key = key - alphabet.length();
			}
		}
		int position = 0;
		String letter = "";
		char alphaChar;
		for (int i = 0; i < usersInput.length(); i++) {
			char ch = usersInput.charAt(i);
			letter = Character.toString(ch);
			if (alphabet.toUpperCase().contains(letter)) {
				position = alphabet.toUpperCase().indexOf(ch) + key;
				if (position >= alphabet.length()) {
					position = position - alphabet.length();
				}
				if (position < 0) {
					position = position + alphabet.length();
				}
				alphaChar = alphabet.toUpperCase().charAt(position);
				alphaShifter = alphaShifter + alphaChar;
				alphaShifter = alphaShifter.replace("null", "");
			} else {
				System.out.println("characters are not in the alphabet Bye");
				input.close();
				System.exit(0);
			}
		}
		return alphaShifter;
	}

	/**
	 * this method chunks the message by separating every number of characters
	 * (determined by chunk) by a space
	 * 
	 * @param chunk is an integer that the user decides how many characters they
	 *              want to chunk in their encrypted message
	 * @return returns encrypted which is the variable containing the final
	 *         encrypted message
	 */
	private static String chunksMessage(int chunk) {
		String encrypted = "";
		if (alphaShifter.length() <= chunk) {
			while (true) {
				if (alphaShifter.length() == chunk) {
					break;
				}
				alphaShifter = alphaShifter + "x";
			}
			encrypted = alphaShifter;
		} else if (alphaShifter.length() > chunk) {
			String word = "";
			if (alphaShifter.length() % chunk != 0) {
				while (alphaShifter.length() % chunk != 0) {
					alphaShifter = alphaShifter + " ";
				}
			}
			for (int i = 0; i < alphaShifter.length(); i++) {
				char ch = alphaShifter.charAt(i);
				word = word + ch;
				if (word.length() == chunk) {
					encrypted = encrypted + word + " ";
					word = "";
				}
			}
			encrypted = encrypted.trim();
			int lastChunkPosition = encrypted.lastIndexOf(" ");
			String lastChunk = encrypted.substring(lastChunkPosition);
			while (lastChunk.trim().length() != chunk) {
				encrypted = encrypted + "x";
				lastChunkPosition = encrypted.lastIndexOf(" ");
				lastChunk = encrypted.substring(lastChunkPosition);
			}
		}
		return encrypted;
	}

	public static void main(String[] args) {
		try {
			usersInput = read("Resources/MyTextFile");
		}
		catch(FileNotFoundException e) {
			System.out.println("File cannot be found");
		}
		// test cases
		// num = 1;
		// num = -2;
		// num = 52;
		// enter letter that will loop around
		// enter no message
		// enter something not in alphabet
		usersInput();
		removePunctuationAndSpaces();
		alphabetShifter("abcdefghijklmnopqrstuvwxyz", -2);
		System.out.println("your message is: " + chunksMessage(4));
	}
}