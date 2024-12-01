package entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Translation class.
 * These tests ensure the Translation object is correctly constructed,
 * and its getter methods return the expected values.
 */
class TranslationTest {

    /**
     * Tests the constructor and all getter methods of the Translation class.
     * Verifies that the fields are initialized correctly and the getters return the expected values.
     */
    @Test
    void testConstructorAndGetters() {
        String sourceText = "Hello";
        String translatedText = "Hola";
        String sourceLang = "en";
        String targetLang = "es";

        Translation translation = new Translation(sourceText, translatedText, sourceLang, targetLang);

        assertEquals(sourceText, translation.getSourceText(), "Source text should match the input.");
        assertEquals(translatedText, translation.getTranslatedText(), "Translated text should match the input.");
        assertEquals(sourceLang, translation.getSourceLang(), "Source language code should match the input.");
        assertEquals(targetLang, translation.getTargetLang(), "Target language code should match the input.");
    }
}
