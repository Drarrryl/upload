package use_case.User;

public interface UserOutputBoundary {
    void prepareStartView(UserOutputData user);
    void prepareProfileView(UserOutputData user);
    void prepareMainMenuView();

    void prepareFailView(String error);
}
