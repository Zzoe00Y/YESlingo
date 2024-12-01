package use_case.profile;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import use_case.profile.change_password.*;
import use_case.signup.*;

public class ChangePasswordInteractorTest {

    @Test
    void successChangePasswordTest() {
        ChangePasswordInputData inputData = new ChangePasswordInputData("Emily", "new password", "new password", "old password");
        ChangePasswordUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Emily", "old password");
        userRepository.save(user);

        ChangePasswordOutputBoundary successPresenter = new ChangePasswordOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangePasswordOutputData user) {
                assertEquals("new password", user.getPassword());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToProfileView() {
                // This is expected
            }
        };

        ChangePasswordInputBoundary interactor = new ChangePasswordInteractor(successPresenter, userRepository);
        interactor.execute(inputData);
    }

    @Test
    void failureOldPasswordMismatchTest() {
        ChangePasswordInputData inputData = new ChangePasswordInputData("Emily", "new password", "new password", "wrong");
        ChangePasswordUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Emily", "old password");
        userRepository.save(user);

        ChangePasswordOutputBoundary failurePresenter = new ChangePasswordOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangePasswordOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Old password does not match.", errorMessage);
            }

            @Override
            public void switchToProfileView() {
                fail("Use case failure is unexpected.");
            }
        };

        ChangePasswordInputBoundary interactor = new ChangePasswordInteractor(failurePresenter, userRepository);
        interactor.execute(inputData);
    }

    @Test
    void failurePasswordMismatchTest() {
        ChangePasswordInputData inputData = new ChangePasswordInputData("Emily", "new password", "wrong", "old password");
        ChangePasswordUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Emily", "old password");
        userRepository.save(user);

        ChangePasswordOutputBoundary failurePresenter = new ChangePasswordOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangePasswordOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("New passwords don't match.", errorMessage);
            }

            @Override
            public void switchToProfileView() {
                fail("Use case failure is unexpected.");
            }
        };

        ChangePasswordInputBoundary interactor = new ChangePasswordInteractor(failurePresenter, userRepository);
        interactor.execute(inputData);
    }
}
