import java.util.*;
import java.io.*;


public class EvilHangMan extends Hangman{
	private String[] Wordlist = new String[235000];// to store the dictionary
	private int numWords = 0;// count the number of possible secret words.
	private int secretStringLength;// the length of the secret string
	private boolean GuessResult = false;

	public EvilHangMan(int StringLength, int numGuesses) {
		remainingGuesses = numGuesses;
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
			currentGuess += "_ ";
		}
		Scanner.close();
	}

	


	public int numLettersRemaining() {
		return 26; // because they never get one right!
	}

	public boolean isWin() {
		return false;
	}

	public boolean gameOver() {
		if (remainingGuesses == 0)
			return true;
		else
			return false;
	}

	
	public boolean makeGuess(char ch) {
		GuessResult = false;
		letterGuess = ch;
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

				OriginSecretWord = Wordlist[0];
				GuessResult = true;
			} else {
				OriginSecretWord = temp[0];
				numWords = tempWordNum;
				Wordlist = temp;
				remainingGuesses--;
				GuessResult = false;
			}
			if (!GuessResult) {
				letterGuessHistory = letterGuessHistory + letterGuess;
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