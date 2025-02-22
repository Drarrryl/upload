package use_case.MainMenu;

public interface MainMenuOutputBoundary {
    void prepareSuccessView(MainMenuOutputData user);

    void prepareFailView(String error);
}
