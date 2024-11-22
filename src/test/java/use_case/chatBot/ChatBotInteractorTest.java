package use_case.chatBot;

import data_access.InMemoryUserDataAccessObject;
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
    private static final String ENGLISH = "English";

//    @Test
//    void successTest() {
//        ChatBotInputData inputData = new ChatBotInputData("Paul", "password");
//        ChatBotUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
//
//        // For the success test, we need to add Paul to the data access repository before we log in.
//        UserFactory factory = new CommonUserFactory();
//        User user = factory.create("Paul", "password");
//        userRepository.save(user);
//
//        // This creates a successPresenter that tests whether the test case is as we expect.
//        ChatBotOutputBoundary successPresenter = new ChatBotOutputBoundary() {
//            @Override
//            public void prepareSuccessView(ChatBotOutputData user) {
//                assertEquals("Paul", user.getUsername());
//            }
//
//            @Override
//            public void prepareFailView(String error) {
//                fail("Use case failure is unexpected.");
//            }
//        };
//
//        ChatBotInputBoundary interactor = new ChatBotInteractor(userRepository, successPresenter);
//        interactor.execute(inputData);
//    }

    @Test
    void successSentChatTest() {

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

//    @Test
//    void failurePasswordMismatchTest() {
//        ChatBotInputData inputData = new ChatBotInputData("Paul", "wrong");
//        ChatBotUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
//
//        // For this failure test, we need to add Paul to the data access repository before we log in, and
//        // the passwords should not match.
//        UserFactory factory = new CommonUserFactory();
//        User user = factory.create("Paul", "password");
//        userRepository.save(user);
//
//        // This creates a presenter that tests whether the test case is as we expect.
//        ChatBotOutputBoundary failurePresenter = new ChatBotOutputBoundary() {
//            @Override
//            public void prepareSuccessView(ChatBotOutputData user) {
//                // this should never be reached since the test case should fail
//                fail("Use case success is unexpected.");
//            }
//
//            @Override
//            public void prepareFailView(String error) {
//                assertEquals("Incorrect password for \"Paul\".", error);
//            }
//        };
//
//        ChatBotInputBoundary interactor = new ChatBotInteractor(userRepository, failurePresenter);
//        interactor.execute(inputData);
//    }

//    @Test
//    void failureUserDoesNotExistTest() {
//        ChatBotInputData inputData = new ChatBotInputData("Paul", "password");
//        ChatBotUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
//
//        // Add Paul to the repo so that when we check later they already exist
//
//        // This creates a presenter that tests whether the test case is as we expect.
//        ChatBotOutputBoundary failurePresenter = new ChatBotOutputBoundary() {
//            @Override
//            public void prepareSuccessView(ChatBotOutputData user) {
//                // this should never be reached since the test case should fail
//                fail("Use case success is unexpected.");
//            }
//
//            @Override
//            public void prepareFailView(String error) {
//                assertEquals("Paul: Account does not exist.", error);
//            }
//        };
//
//        ChatBotInputBoundary interactor = new ChatBotInteractor(userRepository, failurePresenter);
//        interactor.execute(inputData);
//    }
}