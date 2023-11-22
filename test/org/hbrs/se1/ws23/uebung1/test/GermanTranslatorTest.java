package org.hbrs.se1.ws23.uebung1.test;
/**
 * Student: Anastasiia Pashynska
 * Date: 17.10.23
 **/
import org.hbrs.se1.ws23.uebung1.control.GermanTranslator;
import org.hbrs.se1.ws23.uebung1.control.Translator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GermanTranslatorTest {

    @Test
    void aPositiveTest() {
        GermanTranslator translator = new GermanTranslator();
        String value = translator.translateNumber(1);
        assertEquals(value, "eins");

    }
    @Test
    void aNegativeTest() {
        GermanTranslator translator = new GermanTranslator();
        int number = -5; // x<0
        String s = "Übersetzung der Zahl " + number + " nicht möglich (" + Translator.version + ")";
        assertEquals(s, translator.translateNumber(number));
        number = -555; // x<0
        s = "Übersetzung der Zahl " + number + " nicht möglich (" + Translator.version + ")";
        assertEquals(s, translator.translateNumber(number));

    }
}