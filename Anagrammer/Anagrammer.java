/**
 * Author: Shyam Thandullu
 * Github: https://github.com/s-thnd
 * 
 * The Anagrammer class contains all of the information required
 * in order to play the anagrammer game. 
 */


import java.util.*;
import java.io.*;

public class Anagrammer {

	public static int score = 0;
	public static String usedWords = "";
	
	public static void main(String[] args) throws FileNotFoundException {
		
		
		String target = getWord();
		String shuffled = shuffle(target);
		getIntro();
		System.out.println("Your starting word is: " + shuffled);
		getInputs(shuffled);
		System.out.println("Congrats, your score is: " + score + "!");
		
	}
	
	/**
	 * Prints out the flavor text introduction to the user
	 */
	public static void getIntro() {
		System.out.println("Welcome to ANAGRAMMER!");
		System.out.println("Type \"End game\" to end the game.");
		System.out.println("Type \"Instructions\" for instructions.");
		System.out.println("Type \"Get word\" to print the word again.");
		System.out.println();
	}
	
	/**
	 * Takes in a word, compares it against the target word and perform
	 * the corresponding operation
	 * @param target, the word that contains all of the letters available
	 * @throws FileNotFoundException
	 */
	public static void getInputs(String target) throws FileNotFoundException {
		Scanner scan = new Scanner(System.in);
		boolean endGame = false;
		
		while(!endGame){
			System.out.println();
			System.out.print("Input: ");
			String word = scan.nextLine().toLowerCase().trim();
			if(word.isEmpty()) {
				System.out.println("You didn't enter anything!");
			}			
			else if(word.equalsIgnoreCase("get word")) {
				System.out.println("The word given to you is: " + target);
			}
			else if(word.equalsIgnoreCase("instructions")) {
				System.out.println("Type in a word and press enter to guess.");
				System.out.println("Points are determined based on the length of the word entered.");
				System.out.println("You may NOT reuse any letters.");
			}
			else if(word.equalsIgnoreCase("End game")) {
				System.out.println("You have ended the game.");
				break;
			}
			else if(!isValid(word)) {
				System.out.println(capitalize(word) + " is not a valid word.");
			}
			else if(!checkAnagram(target, word)) {
				System.out.println(capitalize(word) + " is not an anagram of the given word.");
			}
			else if(usedBefore(word, usedWords)) {
				System.out.println(capitalize(word) + " has been used already.");
			}
			if(isValid(word) && checkAnagram(target, word) && !usedBefore(word, usedWords)) {
				System.out.println(capitalize(word) + " is valid. " + "+" + (word.length() * 100) + " points!");
				usedWords += word + " ";
				score += word.length() * 100;
			}
		}	
		scan.close();
	}
	
	/**
	 * Takes a string in and capitalizes the first letter
	 * @param word, the word to capitalize
	 * @return the capitalized string
	 */
	public static String capitalize(String word) {
		String output = "";
		output += word.substring(0, 1).toUpperCase();
		output += word.substring(1, word.length());
		return output;
	}
	
	/**
	 * Checks if the word has already been used
	 * @param word, the word the user has put in
	 * @param usedAlready, the list of words already used
	 * @return whether the word has been used before or not
	 */
	public static boolean usedBefore(String word, String usedAlready) {
		if(usedAlready.contains(word)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if the word is an anagram 
	 * @param target, the word we are checking against
	 * @param word, the word we are checking
	 * @return whether the word is an anagram or not
	 */
	public static boolean checkAnagram(String target, String word) {
	    for (char c : word.toCharArray()) {
	        if (target.indexOf(c) == -1) {
	            return false;
	        }
	        target = target.replaceFirst(Character.toString(c), "");
	    }
	    return true;
	}
	
	/**
	 * Gets a random word from the list of all available 7 letter words
	 * @return a random word from the list
	 * @throws FileNotFoundException
	 */
	public static String getWord() throws FileNotFoundException {
		int randomNum = 1 + (int)(Math.random() * 9203);
		
		File text = new File("SevenLetterWords.txt");
		Scanner scan = new Scanner(text);
		String word = "";
		for(int i = 0; i < randomNum; i++) {
			word = scan.next();
		}
		scan.close();
		return word;
		
	}
	
	/**
	 * Returns whether the word is available in the master list or not
	 * @param input the word we are checking
	 * @return whether the word is valid or not
	 * @throws FileNotFoundException
	 */
	public static boolean isValid(String input) throws FileNotFoundException {
		File text = new File("WordsList.txt");
		Scanner scan = new Scanner(text);
		
		boolean out = false;
		
		while(scan.hasNext()) {
			if(input.equals(scan.next())) {
				out = true;
			}
		}
		
		scan.close();
		return out;
	}
	
	/**
	 * Shuffles the string
	 * @param string, the word we are shuffling
	 * @return the shuffled version of the input string
	 */
	public static String shuffle(String string)
	{
	  List<String> letters = Arrays.asList(string.split(""));
	  Collections.shuffle(letters);
	  String shuffled = "";
	  for (String letter : letters) {
	    shuffled += letter;
	  }
	  return shuffled;
	}

}
