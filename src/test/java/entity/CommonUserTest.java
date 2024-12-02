package entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

/**
 * Unit tests for the CommonUser class.
 * These tests ensure correct behavior of all methods in the CommonUser class,
 * covering object initialization, language settings, chat history management,
 * and user session history.
 */
class CommonUserTest {

    /**
     * Tests the constructor and getter methods for name and password.
     * Ensures that the initialized values are returned correctly.
     */
    @Test
    void testConstructorAndGetters() {
        // Test initialization and getters
        String name = "testUser";
        String password = "testPass";
        CommonUser user = new CommonUser(name, password);

        assertEquals(name, user.getName(), "The name should match the initialized value.");
        assertEquals(password, user.getPassword(), "The password should match the initialized value.");
    }

    /**
     * Tests the input and output language settings.
     * Verifies that the default values are correct and the setters update the fields as expected.
     */
    @Test
    void testLanguageSettings() {
        CommonUser user = new CommonUser("testUser", "testPass");

        assertEquals("ENGLISH", user.getInputLan(), "Default input language should be ENGLISH.");
        assertEquals("ENGLISH", user.getOutputLan(), "Default output language should be ENGLISH.");

        user.setInputLan("FRENCH");
        user.setOutputLan("SPANISH");
        assertEquals("FRENCH", user.getInputLan(), "Input language should be updated to FRENCH.");
        assertEquals("SPANISH", user.getOutputLan(), "Output language should be updated to SPANISH.");
    }

    /**
     * Tests the chat history messages display list.
     * Ensures that messages can be added and retrieved correctly.
     */
    @Test
    void testChatHistoryMessagesDisplay() {
        CommonUser user = new CommonUser("testUser", "testPass");

        assertTrue(user.getChatHistoryMessagesDisplay().isEmpty(), "Chat history messages display should be empty.");

        ChatMessage message = new ChatMessage("user", "Hello");
        user.addChatHistoryMessagesDisplay(message);

        assertEquals(1, user.getChatHistoryMessagesDisplay().size(), "Chat history messages display should contain one message.");
        assertEquals(message, user.getChatHistoryMessagesDisplay().get(0), "The added message should match the retrieved message.");
    }

    /**
     * Tests updating the password using setPassword.
     * Verifies that the password is updated correctly.
     */
    @Test
    void testSetPassword() {
        // Test setting the password
        CommonUser user = new CommonUser("testUser", "testPass");

        String newPassword = "newTestPass";
        user.setPassword(newPassword);

        assertEquals(newPassword, user.getPassword(), "The password should be updated to the new value.");
    }

    /**
     * Tests the chat history messages in English list.
     * Ensures that messages can be added and retrieved correctly.
     */
    @Test
    void testChatHistoryMessagesEng() {
        // Test chat history messages in English
        CommonUser user = new CommonUser("testUser", "testPass");

        assertTrue(user.getChatHistoryMessagesEng().isEmpty(), "Chat history messages should be empty");

        ChatMessage message = new ChatMessage("user", "Hello");
        user.addChatHistoryMessagesEng(message);

        assertEquals(1, user.getChatHistoryMessagesEng().size(), "Chat history messages in English should contain one message.");
        assertEquals(message, user.getChatHistoryMessagesEng().get(0), "The added message should match the retrieved message.");
    }

    /**
     * Tests the history list.
     * Ensures that history entries can be set and retrieved correctly.
     */
    @Test
    void testHistory() {
        CommonUser user = new CommonUser("testUser", "testPass");

        assertTrue(user.getHistory().isEmpty(), "History should be empty initially.");

        ArrayList<String> newHistory = new ArrayList<>();
        newHistory.add("Session 1");
        user.setHistory(newHistory);

        assertEquals(1, user.getHistory().size(), "History should contain one entry.");
        assertEquals("Session 1", user.getHistory().get(0), "The history entry should match the added value.");
    }
}
