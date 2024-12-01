package use_case.profile;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;
import use_case.profile.change_language.*;
import use_case.profile.change_password.*;
import view.ChangeLanguageView;

import static org.junit.jupiter.api.Assertions.*;

public class ChangeLanguageInteractorTest {

    @Test
    void successChangeLanguageTest() {
        ChangeLanguageView.LanguageItem languageItem = new ChangeLanguageView.LanguageItem("Chinese", "zh-CN");
        ChangeLanguageInputData inputData = new ChangeLanguageInputData("Emily", languageItem);
        ChangeLanguageUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Emily", "password");
        userRepository.save(user);

        ChangeLanguageOutputBoundary successPresenter = new ChangeLanguageOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangeLanguageOutputData user) {
                assertEquals("Chinese", user.getLanguage().getDisplayName());
            }

            @Override
            public void switchToProfileView() {
                fail("Use case failure is unexpected.");
            }
        };

        ChangeLanguageInputBoundary interactor = new ChangeLanguageInteractor(successPresenter, userRepository);
        interactor.execute(inputData);
    }

    @Test
    void switchToProfileViewTest() {
        ChangeLanguageUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        final boolean[] isSwitchToProfileViewCalled = {false};

        ChangeLanguageOutputBoundary testPresenter = new ChangeLanguageOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangeLanguageOutputData user) {
                fail("prepareSuccessView should not be called in this test.");
            }

            @Override
            public void switchToProfileView() {
                isSwitchToProfileViewCalled[0] = true;
            }
        };

        ChangeLanguageInputBoundary interactor = new ChangeLanguageInteractor(testPresenter, userRepository);
        interactor.switchToProfileView();
        assertTrue(isSwitchToProfileViewCalled[0], "switchToProfileView was not called as expected.");
    }
}
