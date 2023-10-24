package org.hbrs.se1.ws23.uebung1.control;
/**
 * Student: Anastasiia Pashynska
 * Date: 17.10.23
 */
public class TranslatorFactory {
    // Hier wird eine Instanz von GermanTranslator erstellt und zurückgegeben.
    public static Translator createGermanTranslator() {
        return new GermanTranslator();
    }
}
