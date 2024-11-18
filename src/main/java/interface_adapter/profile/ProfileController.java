package interface_adapter.profile;

import use_case.profile.ProfileInputBoundary;

public class ProfileController {
    private final ProfileInputBoundary profileInputBoundary;

    public ProfileController(ProfileInputBoundary profileInteractor) {
        this.profileInputBoundary = profileInteractor;
    }
}
