/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {

	private Dictionary dictionary;

	public ObservableList<String> list = FXCollections.observableArrayList("English", "Italian");

	/*??????*/ private final static boolean dichotomicSearch= true;
	private final static boolean linearSearch= false;
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ChoiceBox<String> choiceBox;

	@FXML
	private TextArea TextInput;

	@FXML
	private Button btnCheck;

	@FXML
	private TextArea textWrongWord;

	@FXML
	private Label labelNumberError;

	@FXML
	private Button btnClear;

	@FXML
	private Label labelTime;

	@FXML
	void doSpellCheck(ActionEvent event) {
		List<Word> word = new LinkedList<>();
		List<Word> wrongWord = new LinkedList<>();
	this.textWrongWord.clear();
		String language = choiceBox.getValue();
	if(language==null) {
		this.textWrongWord.setText("Devi selezionare una lingua");
		return;
	}
	
	if(!dictionary.loadDictionary(language)) {
		this.textWrongWord.setText("Errore nel caricamento del dizionario");
		return;
	}
		
String input=this.TextInput.getText();
if(input.isEmpty()) {
	this.textWrongWord.setText("Inserire un testo da correggere");
	return;
}
		input = input.replaceAll("[\\=.,\\?\\/#!$%\\^$\\*;:{}\\-\\_()\\[\\]\"]", "");
		input= input.replaceAll("\n", " ");
	
		StringTokenizer st = new StringTokenizer(input, " ");
		while (st.hasMoreTokens()) {
			word.add(new Word(st.nextToken()));
		}
		
		long start= System.nanoTime();
		if(dichotomicSearch) {
			wrongWord= dictionary.spellCheckTestDichotomic(word);
		}else if(linearSearch) {
			wrongWord= dictionary.spellCheckTestLinear(word);
		}else {
			wrongWord = dictionary.spellCheckTest(word);
		}
		
		long end= System.nanoTime();
		
		int numErrori=0;
		
		String output = "";
		for (Word w : wrongWord) {
			output += w.getWord() + "\n";
			numErrori++;
		}
		/*Oppure
		 * int numErrori = 0;
		   StringBuilder richText = new StringBuilder();

		for (RichWord r : outputTextList) {
			if (!r.isCorrect()) {
				numErrori++;
				richText.append(r.getWord() + "\n");
			}
		}
		 * 
		 */
		this.textWrongWord.setText(output);
		this.labelNumberError.setText("The text contais "+ numErrori+" errors!");
		this.labelTime.setText("Spell check completed in "+ (end-start)/1E9+ " seconds");
	}

	@FXML
	void doClear(ActionEvent event) {

		this.textWrongWord.clear();
		this.TextInput.clear();
		this.labelNumberError.setText("Numbers of errors: ");
		this.labelTime.setText("Spell Check time: ");
		this.choiceBox.setValue(null);
	}

	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
		 /*
		  * txtDaCorreggere.setDisable(true);
    	txtDaCorreggere.setText("Selezionare una lingua");
    	
    	txtCorretto.setDisable(true);
    	boxLingua.getItems().addAll("English","Italian");
    	
    	spellCheckButton.setDisable(true);
    	clearTextButton.setDisable(true);
 	MEGLIO IMPOSTARE TUTTO DISABILITATO E ABILITARLO QUANDO SI AGGIUNGE IL MODELLO
		  */

	}

	@FXML
	void initialize() {

		assert choiceBox != null : "fx:id=\"choiseBox\" was not injected: check your FXML file 'Scene.fxml'.";
		assert TextInput != null : "fx:id=\"TextInput\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnCheck != null : "fx:id=\"btnTranslate\" was not injected: check your FXML file 'Scene.fxml'.";
		assert textWrongWord != null : "fx:id=\"textWrongWord\" was not injected: check your FXML file 'Scene.fxml'.";
		assert labelNumberError != null : "fx:id=\"labelNumberError\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
		assert labelTime != null : "fx:id=\"labelTime\" was not injected: check your FXML file 'Scene.fxml'.";

		// lista.add("English");
		// lista.add("Italian");
		// list.addAll(lista);
		choiceBox.setItems(list);
		

	}

}
