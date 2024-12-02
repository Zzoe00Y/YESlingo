package use_case.chatBot;

import data_access.InMemoryUserDataAccessObject;
import entity.ChatMessage;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import interface_adapter.chatbot.ChatBotState;
import org.junit.jupiter.api.Test;
import use_case.chatbot.ChatBotOutputData;
import use_case.chatbot.ChatBotOutputBoundary;
import use_case.chatbot.ChatBotInputData;
import use_case.chatbot.ChatBotUserDataAccessInterface;
import use_case.chatbot.ChatBotInputBoundary;
import use_case.chatbot.ChatBotInteractor;

import static org.junit.jupiter.api.Assertions.*;

class ChatBotInteractorTest {
    private static final String ENGLISH = "ENGLISH";
    private static final String FRENCH = "FRENCH";

    @Test
    void sendChat() {
        // For the success test, we need to add Paul to the data access repository before we log in.
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("miao", "password");
        ChatBotUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        userRepository.save(user);

        ChatBotInputData inputData = new ChatBotInputData("Hi", ENGLISH, ENGLISH, "miao");

        // This creates a successPresenter that tests whether the test case is as we expect.
        ChatBotOutputBoundary successPresenter = new ChatBotOutputBoundary() {
            @Override
            public void displayResponse (ChatBotOutputData outputData) {
                assertNotNull(outputData.getResponse());
                assertEquals(false, userRepository.get("miao").getChatHistoryMessagesEng().isEmpty());
            }

            @Override
            public void switchToLoggedInView() {
                fail("Use case success is unexpected.");
            }

            @Override
            public void pullUser(ChatBotState newState) {
                fail("Use case success is unexpected.");
            }
        };

        ChatBotInputBoundary interactor = new ChatBotInteractor(userRepository, successPresenter, factory);

        interactor.sendChat(inputData);
    }

    @Test
    void switchToLoggedInView() {
    }

    @Test
    void pullUser() {
        String username = "miao";
        String inputLan = FRENCH;
        String outputLan = FRENCH;

        // For the success test, we need to add Paul to the data access repository before we log in.
        UserFactory factory = new CommonUserFactory();
        User user = factory.create(username, "password");
        user.setInputLan(inputLan);
        user.setOutputLan(outputLan);
        user.addChatHistoryMessagesDisplay(new ChatMessage("USER", "Hi"));
        user.addChatHistoryMessagesDisplay(new ChatMessage("CHATBOT", "The Cohere Chat API is a tool provided by Cohere, an AI company specializing in natural language processing (NLP) technologies. The Chat API is designed for building conversational AI systems such as chatbots, virtual assistants, and dialogue-based applications. Below are the key details:\n" +
                "\n" +
                "Core Features\n" +
                "Conversational AI:\n" +
                "\n" +
                "The API supports dynamic, human-like conversations using large language models.\n" +
                "Tailored for customer support, information retrieval, and interactive dialogue applications.\n" +
                "Custom Contexts:\n" +
                "\n" +
                "You can provide conversation history or custom instructions to the model for contextual replies.\n" +
                "Supports maintaining context over multiple messages.\n" +
                "Tool Calls:\n" +
                "\n" +
                "The API can integrate tool-use behavior. For instance, the AI can suggest specific tool calls for external actions like retrieving database information or triggering external workflows.\n" +
                "Fine-tuning Support:\n" +
                "\n" +
                "It supports custom fine-tuning for domain-specific applications to adapt responses for unique use cases.\n" +
                "How It Works\n" +
                "Endpoint: The API endpoint for chat functionality is typically /v1/chat.\n" +
                "\n" +
                "Input Structure:\n" +
                "\n" +
                "messages: A list of messages forming the conversation history, including system, user, and assistant messages.\n" +
                "context: Additional context or setup for the assistant to follow in the conversation.\n" +
                "Optional parameters like temperature (for randomness) and max tokens (for response length).\n" +
                "Output:\n" +
                "\n" +
                "The API returns the assistant's response along with metadata such as token usage."));

        ChatBotUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        userRepository.save(user);

        // This creates a successPresenter that tests whether the test case is as we expect.
        ChatBotOutputBoundary successPresenter = new ChatBotOutputBoundary() {
            @Override
            public void displayResponse (ChatBotOutputData outputData) {
                fail("Use case success is expected.");
            }

            @Override
            public void switchToLoggedInView() {
                fail("Use case success is expected.");
            }

            @Override
            public void pullUser(ChatBotState newState) {
                assertEquals(inputLan, newState.getInputLan());
                assertEquals(outputLan, newState.getOutputLan());
                assertEquals(username, newState.getUsername());
                assertEquals("Hi", newState.getChatHistoryMessages().get(0).getMessage());
                assertNotEquals(new ChatBotState().getChatHistoryMessages(), newState.getChatHistoryMessages());
            }
        };

        ChatBotInputBoundary interactor = new ChatBotInteractor(userRepository, successPresenter, factory);

        interactor.pullUser(username);
    }
}