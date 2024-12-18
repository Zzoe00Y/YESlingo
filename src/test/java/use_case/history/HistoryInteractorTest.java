package use_case.history;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import interface_adapter.history.HistoryState;
import org.junit.jupiter.api.Test;
import use_case.profile.change_language.*;

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
            public void pullUser(HistoryOutputData outputData) {
                assertEquals("username", outputData.getUsername());
            }

            @Override
            public void clearAll(HistoryOutputData outputData) {
                fail("Use case success is expected.");
            }
        };

        HistoryInputBoundary interactor = new HistoryInteractor(userRepository, successPresenter, factory);
        interactor.pullUser(new HistoryInputData("username"));
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
            public void pullUser(HistoryOutputData outputData) {
                fail("Use case success is expected.");
            }

            @Override
            public void clearAll(HistoryOutputData outputData) {
                assertTrue(outputData.getHistoryMessages().isEmpty());
                assertTrue(user.getHistory().isEmpty());
            }
        };

        HistoryInputBoundary interactor = new HistoryInteractor(userRepository, successPresenter, factory);
        interactor.clearAll(new HistoryInputData("username"));
    }

    @Test
    void switchToLoggedInViewTest() {
        HistoryUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        final boolean[] isSwitchToLoggedInViewCalled = {false};
        UserFactory factory = new CommonUserFactory();
        HistoryOutputBoundary testPresenter = new HistoryOutputBoundary() {

            @Override
            public void switchToLoggedInView() {
                isSwitchToLoggedInViewCalled[0] = true;
            }

            @Override
            public void pullUser(HistoryOutputData outputData) {
                fail("pullUser should not be called in this test.");
            }

            @Override
            public void clearAll(HistoryOutputData outputData) {
                fail("clearAll should not be called in this test.");
            }
        };

        HistoryInputBoundary interactor = new HistoryInteractor(userRepository, testPresenter, factory);
        interactor.switchToLoggedInView();
        assertTrue(isSwitchToLoggedInViewCalled[0], "switchToLoggedInView was not called as expected.");
    }
}