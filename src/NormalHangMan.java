/**
 * <p>A class that keeps track of the current state of a game of hangman.</p>
 * <p> The class is constructed with a secret word and some number of guesses.</p>
 * <p>Every time a letter is guessed, the state of the game is updated appropriately
 * integrating the guessed letter into the word, updating the number of guesses
 * remaining, etc.</p>
 * 
 * <p>This class can then be used by a user interface to administer a regular game of Hangman.</p>
 */
import java.util.*;
public class NormalHangMan extends Hangman
{
    
    private int LetterLeftNum;//to store the number of the letters in the secret word has not been guessed correctly

    /**
     * Constructor sets up the game to be played with a word and some number of
     * guesses.  The class should have private variables that keep track of:
     * <li>The original secret word
     * <li>The number of guesses remaining
     * <li>The number of letters that still need to be guessed
     * <li>The current state of word to be guessed (e.g. "L A B _ R A _ _ R Y")
     * @param secretWord the word that the player is trying to guess
     * @param numGuesses the number of guesses allowed
     */
    public NormalHangMan(String secretWord, int numGuesses, String LetterHistory){
        OriginSecretWord = secretWord;
        remainingGuesses = numGuesses;
        LetterLeftNum = secretWord.length();
        for(int i = 0; i < secretWord.length(); i++)
        {
        	currentGuess += "_ ";
            for(int j = i; j > 0; j--)
            {
                if(secretWord.charAt(i) == secretWord.charAt(j-1))
                {
                    LetterLeftNum--;//If the letter appears many times in the secret word, it will be counted just once.
                    break;
                }
            }
        }
        letterGuessHistory = LetterHistory;
    }   


   
    public int numLettersRemaining()
    {
        return LetterLeftNum;
    }
    
    
    public boolean isWin()
    {
        if(remainingGuesses == 0)
            return false;//if the user have no chance to guess again, it means the user loses.
        else
            return true;
    }
    public boolean gameOver()
    {
        if(remainingGuesses == 0 || LetterLeftNum == 0)
            return true;
        else
            return false;
    }

    public boolean makeGuess(char ch)
    {
    	if (Character.isLetter(ch) == false) return false;
        boolean tempB = true;
        letterGuess = ch;
        int i;
        for(i = 0; i < OriginSecretWord.length(); i++)
        {
            if(OriginSecretWord.charAt(i) == ch)//if the user guess right, adjust the current state.
            {
                String temp = "";
                for(int j = 0; j < OriginSecretWord.length(); j++)
                {
                    if(OriginSecretWord.charAt(j) == ch)
                    {
                        temp = temp + ch + " ";
                    }
                    else
                    {
                        temp = temp + currentGuess.charAt(2*j) + currentGuess.charAt(2*j+1);              
                    }
                }
                currentGuess = temp;
                tempB = true;
                break;
            }
            else
            {
                tempB = false;
            }
        }
        if(!RepeatInput(ch))
        {
            letterGuessHistory = letterGuessHistory + letterGuess;

            if(tempB)
            {
                LetterLeftNum--;
            }
            else
            {
            	remainingGuesses--;
            }
            return tempB;
        }
        else return false;
    }

    
   
}
    
       