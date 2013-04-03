import java.util.*;
import java.io.*;


public class EvilHangMan implements HangmanGame {
	private String secretWord = "";// To store the secret word
	private int guess;// to store the number of guess for the user
	private String state = "";// store the current guessing situation
	private String letterGuessHistory = "";// store the letters user has tried
	private char l;// the letter the user guess right now
	private String[] Wordlist = new String[235000];// to store the dictionary
	private int numWords = 0;// count the number of possible secret words.
	private int secretStringLength;// the length of the secret string
	private boolean GuessResult = false;

	public EvilHangMan(int StringLength, int numGuesses) {
		guess = numGuesses;
		secretStringLength = StringLength;
		Scanner Scanner = null;
		try {
			Scanner = new Scanner(new File("dictionary.txt"));// read the dictionary
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		int i = 0;
		while (Scanner.hasNext()) {
			String temp = Scanner.nextLine().toUpperCase();
			if (temp.length() == StringLength) {
				Wordlist[i] = temp;
				i++;
				numWords++;
			}
		}

		for (i = 0; i < StringLength; i++) {
			state += "_ ";
		}
		Scanner.close();
	}

	public String getSecretWord() {
		return secretWord;
	}

	public int numGuessesRemaining() {
		return guess;
	}

	public int numLettersRemaining() {
		return 26; // because they never get one right!
	}

	public boolean isWin() {
		return false;
	}

	public boolean gameOver() {
		if (guess == 0)
			return true;
		else
			return false;
	}

	public String lettersGuessed() {
		return letterGuessHistory;
	}

	public String displayGameState() {
		return state;
	}


	public boolean makeGuess(char ch) {
		GuessResult = false;
		l = ch;
		if (Character.isLetter(ch) && !RepeatInput(ch)) {
			// adjust the Wordlist in order to avoid the word with the letter
			// user guessed
			int tempWordNum = 0;
			for (int i = 0; i < numWords; i++) {
				for (int j = 0; j < secretStringLength; j++) {
					if (Wordlist[i].charAt(j) == ch) {
						break;
					} else {
						if (j == secretStringLength - 1) {
							if (Wordlist[i].charAt(j) != ch) {
								tempWordNum++;
							}
						}
					}
				}
			}
			// we choose the words that don't contain the letter the user
			// guessed, and they will be the new possible secret words.
			String[] temp = new String[tempWordNum];
			int tempIndex = 0;
			for (int i = 0; i < numWords; i++) {
				for (int j = 0; j < secretStringLength; j++) {
					if (Wordlist[i].charAt(j) == ch) {
						break;
					} else {
						if (j == secretStringLength - 1) {
							if (Wordlist[i].charAt(j) != ch) {
								temp[tempIndex] = Wordlist[i];
								tempIndex++;
							}
						}
					}
				}
			}
			if (tempWordNum == 0) {

				secretWord = Wordlist[0];
				GuessResult = true;
			} else {
				secretWord = temp[0];
				numWords = tempWordNum;
				Wordlist = temp;
				guess--;
				GuessResult = false;
			}
			if (!GuessResult) {
				letterGuessHistory = letterGuessHistory + l;
			}

		} else return false;
		
		return GuessResult;
	}

    public boolean RepeatInput(char c)
    {
    	for (int i = 0; i < letterGuessHistory.length(); i++) {
    		if (letterGuessHistory.charAt(i) == c) return true;
    	}
    	return false;
    }
}