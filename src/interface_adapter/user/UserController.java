package interface_adapter.user;

import entity.User;
import use_case.User.UserInputBoundary;
import use_case.User.UserInputData;

import java.time.LocalDateTime;

public class UserController {

    private final UserInputBoundary userInteractor;

    public UserController(UserInputBoundary userInputBoundary)
    {
        this.userInteractor = userInputBoundary;
    }

    public void startGame(User user) {
        UserInputData inputData = new UserInputData(user);

        userInteractor.start(inputData);
    }

    public void mainMenu() {
        userInteractor.mainMenu();
    }

    public void profile(User user) {
        UserInputData inputData = new UserInputData(user);

        userInteractor.profile(inputData);
    }
}
