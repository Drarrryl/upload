package interface_adapter.MainMenu;

import interface_adapter.Login.LoginViewModel;
import interface_adapter.user.UserViewModel;
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
        UserViewModel userViewModel = mainMenuViewModel.getUserViewModel();
        viewManager.switchToView(userViewModel.getName());
        viewManager.setResolution(userViewModel.DEFAULT_SIZE);
        userViewModel.setLoggedInUser(user.getUser());
        userViewModel.getState().setUsername(user.getUsername());
        userViewModel.getState().setHighscore(user.getHighscore());
        userViewModel.getState().setPfpStatus(true);
    }

    @Override
    public void prepareFailView(String error) {
        viewManager.showErrorMessage(error);
        viewManager.closeView();
    }
}
