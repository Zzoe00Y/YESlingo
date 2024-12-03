package use_case.profile;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import org.junit.jupiter.api.Test;
import use_case.signup.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ProfileInteractorTest {

    @Test
    void switchToLoggedInViewTest() {
        final boolean[] isSwitchToLoggedInViewCalled = {false};

        ProfileOutputBoundary testPresenter = new ProfileOutputBoundary() {
            @Override
            public void switchToLoggedInView() {
                isSwitchToLoggedInViewCalled[0] = true;
            }

            @Override
            public void switchToChangePasswordView() {
                fail("switchToChangePasswordView should not be called in this test.");
            }

            @Override
            public void switchToLogInView() {
                fail("switchToLogInView should not be called in this test.");
            }

            @Override
            public void switchToChangeLanguageView() {
                fail("switchToChangeLanguageView should not be called in this test.");
            }
        };

        ProfileInputBoundary interactor = new ProfileInteractor(testPresenter);
        interactor.switchToLoggedInView();
        assertTrue(isSwitchToLoggedInViewCalled[0], "switchToLoggedInView was not called as expected.");
    }

    @Test
    void switchToChangePasswordViewTest() {
        final boolean[] isSwitchToChangePasswordViewCalled = {false};

        ProfileOutputBoundary testPresenter = new ProfileOutputBoundary() {
            @Override
            public void switchToLoggedInView() {
                fail("switchToLoggedInView should not be called in this test.");
            }

            @Override
            public void switchToChangePasswordView() {
                isSwitchToChangePasswordViewCalled[0] = true;
            }

            @Override
            public void switchToLogInView() {
                fail("switchToLogInView should not be called in this test.");
            }

            @Override
            public void switchToChangeLanguageView() {
                fail("switchToChangeLanguageView should not be called in this test.");
            }
        };

        ProfileInputBoundary interactor = new ProfileInteractor(testPresenter);
        interactor.switchToChangePasswordView();
        assertTrue(isSwitchToChangePasswordViewCalled[0], "switchToChangePasswordView was not called as expected.");
    }

    @Test
    void switchToLogInViewTest() {
        final boolean[] isSwitchToLogInViewCalled = {false};

        ProfileOutputBoundary testPresenter = new ProfileOutputBoundary() {
            @Override
            public void switchToLoggedInView() {
                fail("switchToLoggedInView should not be called in this test.");
            }

            @Override
            public void switchToChangePasswordView() {
                fail("switchToChangePasswordView should not be called in this test.");
            }

            @Override
            public void switchToLogInView() {
                isSwitchToLogInViewCalled[0] = true;
            }

            @Override
            public void switchToChangeLanguageView() {
                fail("switchToChangeLanguageView should not be called in this test.");
            }
        };

        ProfileInputBoundary interactor = new ProfileInteractor(testPresenter);
        interactor.switchToLogInView();
        assertTrue(isSwitchToLogInViewCalled[0], "switchToLogInView was not called as expected.");
    }

    @Test
    void switchToChangeLanguageViewTest() {
        final boolean[] isSwitchToChangeLanguageViewCalled = {false};

        ProfileOutputBoundary testPresenter = new ProfileOutputBoundary() {
            @Override
            public void switchToLoggedInView() {
                fail("switchToLoggedInView should not be called in this test.");
            }

            @Override
            public void switchToChangePasswordView() {
                fail("switchToChangePasswordView should not be called in this test.");
            }

            @Override
            public void switchToLogInView() {
                fail("switchToLogInView should not be called in this test.");
            }

            @Override
            public void switchToChangeLanguageView() {
                isSwitchToChangeLanguageViewCalled[0] = true;
            }
        };

        ProfileInputBoundary interactor = new ProfileInteractor(testPresenter);
        interactor.switchToChangeLanguageView();
        assertTrue(isSwitchToChangeLanguageViewCalled[0], "switchToChangeLanguageView was not called as expected.");
    }
}
