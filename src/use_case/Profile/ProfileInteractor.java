package use_case.Profile;

import data_access.DataAccess;
import data_access.DataAccessInterface;

public class ProfileInteractor implements ProfileInputBoundary {

    private DataAccessInterface userDataAccess;
    private ProfileOutputBoundary presenter;

    public ProfileInteractor(DataAccessInterface userDataAccess, ProfileOutputBoundary presenter) {
        this.userDataAccess = userDataAccess;
        this.presenter = presenter;
    }

    public void back() {
        presenter.prepareBackView();
    }
}
