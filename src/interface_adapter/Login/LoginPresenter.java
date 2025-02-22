package interface_adapter.Login;

import interface_adapter.MainMenu.MainMenuViewModel;
import use_case.Login.LoginOutputBoundary;
import use_case.Login.LoginOutputData;
import view.ViewManager;

public class LoginPresenter implements LoginOutputBoundary {

    private LoginViewModel loginViewModel;
    private ViewManager viewManager;

    public LoginPresenter(ViewManager viewManager, LoginViewModel loginViewModel)
    {
        this.loginViewModel = loginViewModel;
        this.viewManager = viewManager;
    }


    @Override
    public void prepareSuccessView(LoginOutputData user) {
        MainMenuViewModel mainMenuViewModel = loginViewModel.getMainMenuViewModel();
        loginViewModel.getState().setUsername(user.getUsername());
        viewManager.switchToView(mainMenuViewModel.getName());
        viewManager.setResolution(mainMenuViewModel.DEFAULT_SIZE);
        mainMenuViewModel.setLoggedInUser(user.getUser());
        mainMenuViewModel.getState().setUsername(user.getUsername());
    }

    @Override
    public void prepareFailView(String error) {
        loginViewModel.getState().setUsernameError(error);
        viewManager.showErrorMessage(error);
    }
}
