package entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the ChatMessage class.
 * Ensures that the ChatMessage constructor and getter methods
 * behave as expected, including handling of valid and null inputs.
 */
class ChatMessageTest {

    /**
     * Tests the ChatMessage constructor and its getter methods.
     * Ensures that the values provided to the constructor are correctly
     * retrieved using the getter methods.
     */
    @Test
    void testConstructorAndGetters() {
        String expectedRole = "user";
        String expectedMessage = "Hello, world!";
        ChatMessage chatMessage = new ChatMessage(expectedRole, expectedMessage);

        assertEquals(expectedRole, chatMessage.getRole(), "The role should match the expected value.");

        assertEquals(expectedMessage, chatMessage.getMessage(), "The message should match the expected value.");
    }

    /**
     * Tests the ChatMessage constructor with null inputs.
     * Ensures that the getRole and getMessage methods
     * correctly return null when the respective inputs are null
     */
    @Test
    void testNullValues() {
        ChatMessage chatMessage = new ChatMessage(null, null);

        assertNull(chatMessage.getRole(), "The role should be null.");

        assertNull(chatMessage.getMessage(), "The message should be null.");
    }
}
