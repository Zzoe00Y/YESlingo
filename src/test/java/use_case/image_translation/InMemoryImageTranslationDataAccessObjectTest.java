package use_case.image_translation;

import org.junit.jupiter.api.Test;
import data_access.InMemoryImageTranslationDataAccessObject;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryImageTranslationDataAccessObjectTest {

    @Test
    void testSaveAndRetrieveTranslation() {
        InMemoryImageTranslationDataAccessObject dao = new InMemoryImageTranslationDataAccessObject();

        dao.saveTranslation("1", "Hello", "Hola");
        dao.saveTranslation("2", "World", "Mundo");

        List<String> history = dao.getTranslationHistory();
        assertEquals(2, history.size());
        assertTrue(history.contains("Image ID: 1, Original Text: Hello, Translated Text: Hola"));
        assertTrue(history.contains("Image ID: 2, Original Text: World, Translated Text: Mundo"));
    }

    @Test
    void testDeleteTranslation() {
        InMemoryImageTranslationDataAccessObject dao = new InMemoryImageTranslationDataAccessObject();

        dao.saveTranslation("1", "Hello", "Hola");
        dao.deleteTranslation("1");

        List<String> history = dao.getTranslationHistory();
        assertTrue(history.isEmpty());
    }

    @Test
    void testDeleteNonExistentTranslation() {
        InMemoryImageTranslationDataAccessObject dao = new InMemoryImageTranslationDataAccessObject();

        dao.deleteTranslation("nonexistent"); // Should print a warning but not throw an error
    }
}
