package use_case.loggedin;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class LoggedInInteractorTest {

    @Test
    void switchToProfileViewTest() {
        final boolean[] isSwitchToProfileViewCalled = {false};

        LoggedInOutputBoundary testPresenter = new LoggedInOutputBoundary() {
            @Override
            public void switchToProfileView() {
                isSwitchToProfileViewCalled[0] = true;
            }

            @Override
            public void switchToChatBotView(String username) {
                fail("switchToChatBotView should not be called in this test.");
            }

            @Override
            public void switchToHistoryView(String username) {
                fail("switchToHistoryView should not be called in this test.");
            }
        };

        LoggedInInputBoundary interactor = new LoggedInInteractor(testPresenter);
        interactor.switchToProfileView();
        assertTrue(isSwitchToProfileViewCalled[0], "switchToProfileView was not called as expected.");
    }

    @Test
    void switchToChatBotViewTest() {
        final boolean[] isSwitchToChatBotViewCalled = {false};

        LoggedInOutputBoundary testPresenter = new LoggedInOutputBoundary() {
            @Override
            public void switchToProfileView() {
                fail("switchToProfileView should not be called in this test.");
            }

            @Override
            public void switchToChatBotView(String username) {
                isSwitchToChatBotViewCalled[0] = true;
            }

            @Override
            public void switchToHistoryView(String username) {
                fail("switchToHistoryView should not be called in this test.");
            }
        };

        LoggedInInputBoundary interactor = new LoggedInInteractor(testPresenter);
        interactor.switchToChatBotView("Emily");
        assertTrue(isSwitchToChatBotViewCalled[0], "switchToChatBotView was not called as expected.");
    }

    @Test
    void switchToHistoryViewTest() {
        final boolean[] isSwitchToHistoryViewCalled = {false};

        LoggedInOutputBoundary testPresenter = new LoggedInOutputBoundary() {
            @Override
            public void switchToProfileView() {
                fail("switchToProfileView should not be called in this test.");
            }

            @Override
            public void switchToChatBotView(String username) {
                fail("switchToChatBotView should not be called in this test.");
            }

            @Override
            public void switchToHistoryView(String username) {
                isSwitchToHistoryViewCalled[0] = true;
            }
        };

        LoggedInInputBoundary interactor = new LoggedInInteractor(testPresenter);
        interactor.switchToHistoryView("Emily");
        assertTrue(isSwitchToHistoryViewCalled[0], "switchToHistoryView was not called as expected.");
    }
}
