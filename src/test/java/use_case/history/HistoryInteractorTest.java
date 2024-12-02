package use_case.history;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import interface_adapter.history.HistoryState;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HistoryInteractorTest {

    @Test
    void pullUser() {
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("username", "password");
        ArrayList<String> history = new ArrayList<>();
        history.add("word1");
        user.setHistory(history);

        HistoryUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        userRepository.save(user);

        HistoryOutputBoundary successPresenter = new HistoryOutputBoundary() {

            @Override
            public void switchToLoggedInView() {
                fail("Use case success is expected.");
            }

            @Override
            public void pullUser(HistoryState newState) {
                assertEquals("username", newState.getUsername());
            }

            @Override
            public void clearAll(HistoryState newState) {
                fail("Use case success is expected.");
            }
        };

        HistoryInputBoundary interactor = new HistoryInteractor(userRepository, successPresenter, factory);
        interactor.pullUser("username");
    }

    @Test
    void clearAll() {
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("username", "password");
        ArrayList<String> history = new ArrayList<>();
        history.add("word1");
        user.setHistory(history);

        HistoryUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        userRepository.save(user);

        HistoryOutputBoundary successPresenter = new HistoryOutputBoundary() {

            @Override
            public void switchToLoggedInView() {
                fail("Use case success is expected.");
            }

            @Override
            public void pullUser(HistoryState newState) {
                fail("Use case success is expected.");
            }

            @Override
            public void clearAll(HistoryState newState) {
                assertTrue(newState.getHistoryMessages().isEmpty());
                assertTrue(user.getHistory().isEmpty());
            }
        };

        HistoryInputBoundary interactor = new HistoryInteractor(userRepository, successPresenter, factory);
        interactor.clearAll("username");
    }
}