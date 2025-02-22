package interface_adapter.MainMenu;

import interface_adapter.Login.LoginViewModel;
import use_case.Login.LoginOutputData;
import use_case.MainMenu.MainMenuOutputBoundary;
import use_case.MainMenu.MainMenuOutputData;
import view.ViewManager;

public class MainMenuPresenter implements MainMenuOutputBoundary {
    private MainMenuViewModel mainMenuViewModel;
    private ViewManager viewManager;

    public MainMenuPresenter(ViewManager viewManager, MainMenuViewModel mainMenuViewModel)
    {
        this.mainMenuViewModel = mainMenuViewModel;
        this.viewManager = viewManager;
    }


    @Override
    public void prepareSuccessView(MainMenuOutputData user) {
        mainMenuViewModel.getState().setUsername(user.getUsername());
        viewManager.switchToView(mainMenuViewModel.getUserViewModel().getName());
        mainMenuViewModel.getUserViewModel().setLoggedInUser(user.getUser());
    }

    @Override
    public void prepareFailView(String error) {
        viewManager.showErrorMessage(error);
    }
}
