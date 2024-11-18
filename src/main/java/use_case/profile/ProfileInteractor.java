package use_case.profile;

import entity.UserFactory;

/**
 * The Profile Interactor.
 */
public class ProfileInteractor implements ProfileInputBoundary {
    private final ProfileUserDataAccessInterface profileUserDataAccessInterface;
    private final ProfileOutputBoundary profileOutputBoundary;
    private final UserFactory userFactory;
    public ProfileInteractor(ProfileUserDataAccessInterface profileUserDataAccessInterface,
                             ProfileOutputBoundary profileOutputBoundary,
                             UserFactory userFactory) {
        this.profileUserDataAccessInterface = profileUserDataAccessInterface;
        this.profileOutputBoundary = profileOutputBoundary;
        this.userFactory = userFactory;
    }
}
