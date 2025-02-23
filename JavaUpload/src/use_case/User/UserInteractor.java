package use_case.User;

import Factory.UserFactory;
import data_access.DataAccessInterface;
import entity.User;
import use_case.Signup.SignupInputBoundary;

import java.time.LocalDateTime;
import java.util.List;

public class UserInteractor implements UserInputBoundary {
    final DataAccessInterface userDataAccessObject;
    final UserOutputBoundary userPresenter;
    public UserInteractor(DataAccessInterface userDataAccessInterface,
                                    UserOutputBoundary userOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.userPresenter = userOutputBoundary;
    }

    private boolean emptyName(String name) {
        for (int i = 0; i < name.length()-1; i++) {
            if (name.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }

    @Override
    public void start(UserInputData inputData) {
        User user = inputData.getUser();
        long highscore = userDataAccessObject.readHighscore(user.getUsername());

        user.setHighscore(highscore);

        UserOutputData outputData = new UserOutputData(user, highscore);

        userPresenter.prepareStartView(outputData);
    }

    @Override
    public void mainMenu() {
        userPresenter.prepareMainMenuView();
    }

    @Override
    public void profile(UserInputData inputData) {
        User user = inputData.getUser();
        long highscore = userDataAccessObject.readHighscore(user.getUsername());

        UserOutputData outputData = new UserOutputData(user, highscore);

        userPresenter.prepareProfileView(outputData);
    }
}
