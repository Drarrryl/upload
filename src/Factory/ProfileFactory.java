package Factory;

import data_access.DataAccessInterface;
import interface_adapter.Profile.ProfileController;
import interface_adapter.Profile.ProfilePresenter;
import interface_adapter.Profile.ProfileViewModel;
import use_case.Profile.ProfileInputBoundary;
import use_case.Profile.ProfileInteractor;
import use_case.Profile.ProfileOutputBoundary;
import view.Profile.ProfileView;
import view.ViewManager;

public class ProfileFactory {
    public static ProfileView createView(ViewManager viewManager,
                                         ProfileViewModel profileViewModel,
                                         DataAccessInterface userDataAccess) {
        ProfileController profileController = createController(viewManager, profileViewModel, userDataAccess);
        return new ProfileView(profileViewModel, profileController);
    }

    public static ProfileController createController(ViewManager viewManager,
                                                     ProfileViewModel profileViewModel,
                                                     DataAccessInterface userDataAccess) {
        ProfileOutputBoundary profilePresenter = new ProfilePresenter(viewManager, profileViewModel);
        ProfileInputBoundary profileInteractor = new ProfileInteractor(userDataAccess, profilePresenter);
        return new ProfileController(profileInteractor);
    }
}
