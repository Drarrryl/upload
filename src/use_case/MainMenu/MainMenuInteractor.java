package use_case.MainMenu;

import data_access.DataAccessInterface;
import entity.User;

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
        long score = userDataAccessObject.readHighscore(username);
        User user = userDataAccessObject.readUser(username);

        MainMenuOutputData data = new MainMenuOutputData(username, score, user);

        if (buttonName.equals("Start")) {
            mainMenuPresenter.prepareSuccessView(data);
        } else if (buttonName.equals("Exit")) {
            mainMenuPresenter.prepareFailView("Closing software");
        }

    }
}
