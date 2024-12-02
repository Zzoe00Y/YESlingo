package use_case.text_translation;

import entity.Translation;
import entity.User;
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

            @Override
            public void save(User user) {
            }

            @Override
            public User get(String username) {
                return null;
            }
        };

        final boolean[] successViewCalled = {false};

        TextTranslationOutputBoundary mockOutputBoundary = new TextTranslationOutputBoundary() {
            @Override
            public void prepareSuccessView(TextTranslationOutputData outputData) {
                assertEquals("Hello world", outputData.getSourceText());
                assertEquals("Hola mundo", outputData.getTranslatedText());
                assertEquals("en", outputData.getSourceLang());
                assertEquals("es", outputData.getTargetLang());
                successViewCalled[0] = true;
            }

            @Override
            public void prepareFailView(String error) {
                fail("Translation should not fail in success test case");
            }
        };

        TextTranslationInputData inputData = new TextTranslationInputData(
                "Hello world", "en", "es", "user1"
        );

        TextTranslationInteractor interactor = new TextTranslationInteractor(
                mockDataAccess, mockOutputBoundary, null
        );
        interactor.translate(inputData);

        assertTrue("Success view should have been called", successViewCalled[0]);
    }

    @Test
    public void failureTranslationServiceErrorTest() {
        TextTranslationDataAccessInterface mockDataAccess = new TextTranslationDataAccessInterface() {
            @Override
            public Translation translateText(String sourceText, String sourceLang, String targetLang) {
                throw new RuntimeException("Translation service unavailable");
            }

            @Override
            public void save(User user) {
            }

            @Override
            public User get(String username) {
                return null;
            }
        };

        final boolean[] failViewCalled = {false};

        TextTranslationOutputBoundary mockOutputBoundary = new TextTranslationOutputBoundary() {
            @Override
            public void prepareSuccessView(TextTranslationOutputData outputData) {
                fail("Translation should have failed but succeeded");
            }

            @Override
            public void prepareFailView(String error) {
                assertTrue(error.contains("Translation failed"));
                assertTrue(error.contains("Translation service unavailable"));
                failViewCalled[0] = true;
            }
        };

        TextTranslationInputData inputData = new TextTranslationInputData(
                "Hello world", "en", "es", "user1"
        );

        TextTranslationInteractor interactor = new TextTranslationInteractor(
                mockDataAccess, mockOutputBoundary, null
        );
        interactor.translate(inputData);

        assertTrue("Fail view should have been called", failViewCalled[0]);
    }

    @Test
    public void invalidLanguageCodeTest() {
        TextTranslationDataAccessInterface mockDataAccess = new TextTranslationDataAccessInterface() {
            @Override
            public Translation translateText(String sourceText, String sourceLang, String targetLang) {
                throw new IllegalArgumentException("Invalid language code: xyz");
            }

            @Override
            public void save(User user) {
            }

            @Override
            public User get(String username) {
                return null;
            }
        };

        final boolean[] failViewCalled = {false};

        TextTranslationOutputBoundary mockOutputBoundary = new TextTranslationOutputBoundary() {
            @Override
            public void prepareSuccessView(TextTranslationOutputData outputData) {
                fail("Translation should have failed with invalid language code");
            }

            @Override
            public void prepareFailView(String error) {
                assertTrue(error.contains("Translation failed"));
                assertTrue(error.contains("Invalid language code"));
                failViewCalled[0] = true;
            }
        };

        TextTranslationInputData inputData = new TextTranslationInputData(
                "Hello world", "xyz", "es", "user1"
        );

        TextTranslationInteractor interactor = new TextTranslationInteractor(
                mockDataAccess, mockOutputBoundary, null
        );
        interactor.translate(inputData);

        assertTrue("Fail view should have been called", failViewCalled[0]);
    }
}