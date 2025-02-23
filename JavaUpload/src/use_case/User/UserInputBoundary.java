package use_case.User;

public interface UserInputBoundary {
    void start(UserInputData userInputData);
    void mainMenu();
    void profile(UserInputData userInputData);
}
