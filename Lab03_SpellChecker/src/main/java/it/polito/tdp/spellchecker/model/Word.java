package it.polito.tdp.spellchecker.model;
/**
 * Classe che definisce se ogni parola inserita è scritta giusta.
 * @author Simona
 *
 */
public class Word {
	private String word;
	private boolean isRight;
	
	public Word(String word) {
		this.word=word;
		this.isRight=false;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public boolean isRight() {
		return isRight;
	}

	public void setRight(boolean isRight) {
		this.isRight = isRight;
	}

	@Override
	public String toString() {
		return word + "\n";
	}

	
}
