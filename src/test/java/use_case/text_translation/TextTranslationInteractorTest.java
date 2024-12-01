package use_case.text_translation;

import entity.Translation;
import org.junit.Test;
import static org.junit.Assert.*;

public class TextTranslationInteractorTest {

    @Test
    public void successTextTranslationTest() {
        TextTranslationDataAccessInterface mockDataAccess = new TextTranslationDataAccessInterface() {
            @Override
            public Translation translateText(String sourceText, String sourceLang, String targetLang) {
                assertEquals("Hello world", sourceText);
                assertEquals("en", sourceLang);
                assertEquals("es", targetLang);

                return new Translation("Hello world", "Hola mundo", "en", "es");
            }
        };

        TextTranslationOutputBoundary mockOutputBoundary = new TextTranslationOutputBoundary() {
            @Override
            public TextTranslationOutputData prepareSuccessView(TextTranslationOutputData outputData) {
                assertEquals("Hello world", outputData.getSourceText());
                assertEquals("Hola mundo", outputData.getTranslatedText());
                assertEquals("en", outputData.getSourceLang());
                assertEquals("es", outputData.getTargetLang());
                return outputData;
            }

            @Override
            public TextTranslationOutputData prepareFailView(String error) {
                fail("Translation should not fail in success test case");
                return null;
            }
        };

        TextTranslationInputData inputData = new TextTranslationInputData(
                "Hello world", "en", "es"
        );

        TextTranslationInteractor interactor = new TextTranslationInteractor(
                mockDataAccess, mockOutputBoundary
        );
        TextTranslationOutputData result = interactor.translate(inputData);

        assertNotNull(result);
        assertEquals("Hola mundo", result.getTranslatedText());
    }

    @Test
    public void failureTranslationServiceErrorTest() {
        TextTranslationDataAccessInterface mockDataAccess = new TextTranslationDataAccessInterface() {
            @Override
            public Translation translateText(String sourceText, String sourceLang, String targetLang) {
                throw new RuntimeException("Translation service unavailable");
            }
        };

        TextTranslationOutputBoundary mockOutputBoundary = new TextTranslationOutputBoundary() {
            @Override
            public TextTranslationOutputData prepareSuccessView(TextTranslationOutputData outputData) {
                fail("Translation should have failed but succeeded");
                return null;
            }

            @Override
            public TextTranslationOutputData prepareFailView(String error) {
                assertTrue(error.contains("Translation failed"));
                assertTrue(error.contains("Translation service unavailable"));

                return new TextTranslationOutputData(
                        "Hello world", "", "en", "es"
                );
            }
        };

        TextTranslationInputData inputData = new TextTranslationInputData(
                "Hello world", "en", "es"
        );

        TextTranslationInteractor interactor = new TextTranslationInteractor(
                mockDataAccess, mockOutputBoundary
        );
        TextTranslationOutputData result = interactor.translate(inputData);

        assertNotNull(result);
        assertEquals("", result.getTranslatedText());
    }

    @Test
    public void invalidLanguageCodeTest() {
        TextTranslationDataAccessInterface mockDataAccess = new TextTranslationDataAccessInterface() {
            @Override
            public Translation translateText(String sourceText, String sourceLang, String targetLang) {
                throw new IllegalArgumentException("Invalid language code: xyz");
            }
        };

        TextTranslationOutputBoundary mockOutputBoundary = new TextTranslationOutputBoundary() {
            @Override
            public TextTranslationOutputData prepareSuccessView(TextTranslationOutputData outputData) {
                fail("Translation should have failed with invalid language code");
                return null;
            }

            @Override
            public TextTranslationOutputData prepareFailView(String error) {
                assertTrue(error.contains("Translation failed"));
                assertTrue(error.contains("Invalid language code"));
                return new TextTranslationOutputData(
                        "Hello world", "", "xyz", "es"
                );
            }
        };

        TextTranslationInputData inputData = new TextTranslationInputData(
                "Hello world", "xyz", "es"
        );

        TextTranslationInteractor interactor = new TextTranslationInteractor(
                mockDataAccess, mockOutputBoundary
        );
        TextTranslationOutputData result = interactor.translate(inputData);

        assertNotNull(result);
        assertEquals("", result.getTranslatedText());
    }
}
