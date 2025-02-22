package interface_adapter.user;

import interface_adapter.Game.GameViewModel;
import interface_adapter.MainMenu.MainMenuViewModel;
import interface_adapter.Profile.ProfileState;
import interface_adapter.Profile.ProfileViewModel;
import use_case.User.UserOutputBoundary;
import use_case.User.UserOutputData;
import view.ViewManager;

import javax.swing.*;

public class UserPresenter implements UserOutputBoundary
{
    ViewManager viewManager;
    UserViewModel userViewModel;

    public UserPresenter(ViewManager viewManager, UserViewModel userViewModel)
    {
        this.viewManager = viewManager;
        this.userViewModel = userViewModel;
    }

    @Override
    public void prepareStartView(UserOutputData user) {
        GameViewModel gameViewModel = userViewModel.getGameViewModel();
        viewManager.switchToView(gameViewModel.getName());
        viewManager.setResolution(gameViewModel.DEFAULT_SIZE);
        gameViewModel.setLoggedInUser(user.getUser());
        gameViewModel.getState().setUser(user.getUser());
        gameViewModel.getState().setStatus(true);
    }

    @Override
    public void prepareProfileView(UserOutputData user) {
        ProfileViewModel profileViewModel = userViewModel.getProfileViewModel();
        viewManager.switchToView(profileViewModel.getName());
        viewManager.setResolution(profileViewModel.DEFAULT_SIZE);
        ProfileState state = profileViewModel.getProfileState();
        state.setLoggedInUser(user.getUser());
        state.setUsername(user.getUser().getUsername());
        state.setHighscore(user.getHighscore());
        state.setStatus(true);
    }


    @Override
    public void prepareMainMenuView() {
        MainMenuViewModel mainMenuViewModel = (MainMenuViewModel) userViewModel.getViewManager().getViewModel("Main Menu");
        viewManager.switchToView(mainMenuViewModel.getName());
        viewManager.setResolution(mainMenuViewModel.DEFAULT_SIZE);
    }

    @Override
    public void prepareFailView(String error) {
        viewManager.showErrorMessage(error);
        viewManager.closeView();
    }
}
