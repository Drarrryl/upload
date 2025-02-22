package use_case.MainMenu;

import data_access.DataAccessInterface;

public class MainMenuInteractor implements MainMenuInputBoundary {
    final DataAccessInterface userDataAccessObject;
    final MainMenuOutputBoundary mainMenuPresenter;

    public MainMenuInteractor(DataAccessInterface userDataAccessObject,
                              MainMenuOutputBoundary mainMenuOutputBoundary) {
        this.userDataAccessObject = userDataAccessObject;
        this.mainMenuPresenter = mainMenuOutputBoundary;
    }

    @Override
    public void execute(MainMenuInputData mainMenuInputData) {
        String buttonName = mainMenuInputData.getButtonName();
        String username = mainMenuInputData.getUsername();

    }
}
