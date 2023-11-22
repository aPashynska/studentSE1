package org.hbrs.se1.ws23.uebung1.view;
/**
 * Student: Anastasiia Pashynska
 * Date: 17.10.23
 */
import org.hbrs.se1.ws23.uebung1.control.TranslatorFactory;
import org.hbrs.se1.ws23.uebung1.control.Translator;
public class Client {

		/*
		 * Methode zur Ausgabe einer Zahl auf der Console
		 * (auch bezeichnet als CLI, Terminal)
		 *
		 */
		 void display( int aNumber ){
			// In dieser Methode soll die Methode translateNumber
			// mit dem übergegebenen Wert der Variable aNumber
			// aufgerufen werden.
			//
			// Strenge Implementierung gegen das Interface Translator gewuenscht!
			Translator translator = TranslatorFactory.createGermanTranslator();
			String translation = translator.translateNumber(aNumber);
			System.out.println("Das Ergebnis der Berechnung: " + translation);

		 }
}





