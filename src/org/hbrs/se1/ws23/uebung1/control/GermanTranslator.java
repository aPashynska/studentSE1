package org.hbrs.se1.ws23.uebung1.control;
/**
 * Student: Anastasiia Pashynska
 * Date: 17.10.23
 */
public class GermanTranslator implements Translator {
	private String [] dictionary = {"eins", "zwei", "drei", "vier", "fünf", "sechs",
			"sieben", "acht", "neun", "zehn"};
	public String date = "Okt/2023"; // Default-Wert

	/**
	 * Methode zur Übersetzung einer Zahl in eine String-Repraesentation
	 */
	public String translateNumber( int number ) {
		// [ihr Source Code aus Übung 1-2]
		// Gültige Zahl, gibt die Übersetzung aus dem Array zurück.
		try {
			return dictionary[number-1];
		} catch (ArrayIndexOutOfBoundsException e) {
			return "Übersetzung der Zahl " + number + " nicht möglich (" + Translator.version + ")";
			}

	}

	/**
	 * Objektmethode der Klasse GermanTranslator zur Ausgabe einer Info.
	 */
	public void printInfo(){
		System.out.println( "GermanTranslator v1.9, erzeugt am " + this.date );
	}

	/**
	 * Setzen des Datums, wann der Uebersetzer erzeugt wurde (Format: Monat/Jahr (Beispiel: "Okt/2022"))
	 * Das Datum sollte system-intern durch eine Control-Klasse gesetzt werden und nicht von externen View-Klassen
	 */
	public void setDate( String date ) {
		this.date = date;
	}

}
