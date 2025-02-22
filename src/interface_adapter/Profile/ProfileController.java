package interface_adapter.Profile;

import use_case.Profile.ProfileInputBoundary;

public class ProfileController {

    private ProfileInputBoundary profileInteractor;

    public ProfileController(ProfileInputBoundary profileInteractor) {
        this.profileInteractor = profileInteractor;
    }

    public void back() {
        profileInteractor.back();
    }
}
