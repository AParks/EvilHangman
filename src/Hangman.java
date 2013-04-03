public abstract class Hangman {

	protected String OriginSecretWord = "";// To store the secret word
	protected int remainingGuesses;// to store the number of guess for the user
	protected String currentGuess = "";// store the current guessing situation
	protected String letterGuessHistory = "";// store the letter user has tried
	protected char letterGuess;// the letter the user guess right now

	public int numGuessesRemaining() {
		return remainingGuesses;
	}

	public String getSecretWord() {
		return OriginSecretWord;
	}

	public String displayGameState() {
		return currentGuess;
	}
	
	public String lettersGuessed() {
		return letterGuessHistory;
	}
	
    public boolean RepeatInput(char c)
    {
    	for (int i = 0; i < letterGuessHistory.length(); i++) {
    		if (letterGuessHistory.charAt(i) == c) return true;
    	}
    	return false;
    }
    
    /**
     * Simulates the player guessing a letter in the word and updates the state of game
     * accordingly -- number of guesses remaining, number of letters 
     * if the guessed letter is not in the word, and hasn't been guessed yet, numGuesses is decremented
     * if the guessed letter is in the word, and hasn't been guessed yet, then update the current state of
     * the guessed word to reveal the position(s) of the letter(s) that was just guessed, and update numLettersRemaining.
     * There should be no guess penalty for guessing a letter that has already been 
     * guessed, just return false.  
     * @param ch the char that is the next letter to be guessed on the word
     * @return true if the game isn't over and the guessed letter was in the word, false otherwise
     */
    public abstract boolean makeGuess(char ch);
     
    
    /**
     * @return true if there are no more letters to be guessed and there is still at least one guess remaining
     */
    public abstract boolean isWin(); 
    
    /** 
     * @return true if either num guesses remaining is 0 or num letters remaining to be guessed is 0.
     */
    public abstract boolean gameOver(); 
    

    /**
     * The number of letters remaining to be guessed in the secret word - i.e.
     * the number of blank spaces the player sees, which may be different from
     * the actually number of letters it will take to fill those blanks.
     * @return the number of letters in the secret word that still have to be guessed
     */
    public abstract int numLettersRemaining();
    

    


}
