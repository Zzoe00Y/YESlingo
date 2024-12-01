package use_case.profile;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;
import use_case.profile.change_language.ChangeLanguageInputData;
import use_case.profile.change_language.ChangeLanguageUserDataAccessInterface;
import use_case.profile.change_password.*;
import view.ChangeLanguageView;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ChangeLanguageInteractorTest {

//    @Test
//    void successChangeLanguageTest() {
//        ChangeLanguageView.LanguageItem languageItem = new ChangeLanguageView.LanguageItem("English", "en");
//        ChangeLanguageInputData inputData = new ChangeLanguageInputData("Emily", languageItem);
//        ChangeLanguageUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
//
//        UserFactory factory = new CommonUserFactory();
//        User user = factory.create("Emily", "password");
//        ((InMemoryUserDataAccessObject) userRepository).save(user);
//
//        ChangePasswordOutputBoundary successPresenter = new ChangePasswordOutputBoundary() {
//            @Override
//            public void prepareSuccessView(ChangePasswordOutputData user) {
//                assertEquals("new password", user.getPassword());
//            }
//
//            @Override
//            public void prepareFailView(String errorMessage) {
//                fail("Use case failure is unexpected.");
//            }
//
//            @Override
//            public void switchToProfileView() {
//                fail("Use case failure is unexpected.");
//            }
//        };
//
//        ChangePasswordInputBoundary interactor = new ChangePasswordInteractor(successPresenter, userRepository);
//        interactor.execute(inputData);
//    }
}
