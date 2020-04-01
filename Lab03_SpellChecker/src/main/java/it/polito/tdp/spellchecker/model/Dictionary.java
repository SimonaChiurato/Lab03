package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary {

	Set<String> dictionary;
	List<Word> word;

	public Dictionary() {
		dictionary = new LinkedHashSet<>();
		word = new LinkedList<>();
	}

	public boolean loadDictionary(String language) {

		try {
			FileReader fr = new FileReader(language + ".txt");
			BufferedReader br = new BufferedReader(fr);
			String riga;

			while ((riga = br.readLine()) != null) {
				dictionary.add(riga);

			}
			fr.close();
			return true;
		} catch (IOException io) {
			System.out.println("Errore lettura file");
			return false;

		}
	}

	/*
	 * }else if(language.equals("Italian")) { try { FileReader fr = new
	 * FileReader("Italian.txt"); BufferedReader br = new BufferedReader(fr); String
	 * riga;
	 * 
	 * while ((riga = br.readLine()) != null) { dictionary.add(riga);
	 * 
	 * } fr.close();
	 * 
	 * }catch(IOException io) { System.out.println("Errore lettura file"); } }
	 */

	public List<Word> spellCheckTest(List<Word> word) {
		//List<Word> rigthWord = new LinkedList<>();
		List<Word> rigthWord= new ArrayList<>();
		for (Word w : word) {
			if (dictionary.contains(w.getWord().toLowerCase())) {
				w.setRight(true);
				rigthWord.add(w);
			}
		}
		word.removeAll(rigthWord);
		return word;
	}

	public List<Word> spellCheckTestLinear(List<Word> word) {
		List<Word> rigthWord = new LinkedList<>();
		// List<Word> rigthWord= new ArrayList<>();
		for (Word w : word) {
			for(String parola: dictionary) {
				if(parola.equalsIgnoreCase(w.getWord())){
					w.setRight(true);
					rigthWord.add(w);
					break;
				}
			}
		}
		word.removeAll(rigthWord);
		return word;
	}

	public List<Word> spellCheckTestDichotomic(List<Word> word) {
		List<Word> rigthWord = new LinkedList<>();
		// List<Word> rigthWord= new ArrayList<>();
		for (Word w : word) {
		if(binarySearch(w.getWord().toLowerCase())) {
			w.setRight(true);
			rigthWord.add(w);
		}
		}
		word.removeAll(rigthWord);
		return word;
	}

	private boolean binarySearch(String parola) {
		List<String> dizionario= new ArrayList<>();
		dizionario.addAll(dictionary);
		int inizio=0;
		int fine=dictionary.size();
		
		while(inizio!=fine) {
			int medio=(fine-inizio)/2;
			if(parola.equals(dizionario.get(medio).toLowerCase())) {
				return true;
			}else if(parola.compareToIgnoreCase(dizionario.get(medio))>0) {
				inizio=medio+1;
			}else {
				fine=medio;
			}
		}
		return false;
	}
}
