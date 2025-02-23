package app;

import Factory.*;
import data_access.DataAccess;
import data_access.DataAccessInterface;
import interface_adapter.Game.GameViewModel;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.MainMenu.MainMenuViewModel;
import interface_adapter.Options.OptionsViewModel;
import interface_adapter.Profile.ProfileViewModel;
import interface_adapter.Signup.SignupViewModel;
import interface_adapter.user.UserViewModel;
import view.Game.GameView;
import view.Login.LoginView;
import view.MainMenu.MainMenuView;
import view.Options.OptionsView;
import view.Profile.ProfileView;
import view.Signup.SignupView;
import view.User.UserView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        DataAccessInterface dataAccess = new DataAccess();

        JFrame applicationFrame = new JFrame("Team Task Manager");
        applicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ViewManager viewManager = new ViewManager(applicationFrame);

        SignupViewModel signupViewModel = new SignupViewModel(viewManager);
        SignupView signupView = SignupFactory.createSignupView(viewManager, signupViewModel, dataAccess, new UserFactory());

        GameViewModel gameViewModel = new GameViewModel(viewManager);
        GameView gameView = GameFactory.createGameView(viewManager, gameViewModel, dataAccess);

        ProfileViewModel profileViewModel = new ProfileViewModel(viewManager);
        ProfileView profileView = ProfileFactory.createView(viewManager, profileViewModel, dataAccess);

        UserViewModel userViewModel = new UserViewModel(viewManager, gameViewModel, profileViewModel);
        UserView userView = UserFactory.createUserView(viewManager, userViewModel, dataAccess);

        OptionsViewModel optionsViewModel = new OptionsViewModel(viewManager);
        OptionsView optionsView = OptionsFactory.createOptionsView(viewManager, optionsViewModel, dataAccess);

        MainMenuViewModel mainMenuViewModel = new MainMenuViewModel(viewManager, optionsViewModel, userViewModel);
        MainMenuView mainMenuView = MainMenuFactory.createMainMenuView(viewManager, mainMenuViewModel, dataAccess);

        LoginViewModel loginViewModel = new LoginViewModel(viewManager, signupViewModel, mainMenuViewModel);
        LoginView loginView = LoginFactory.createLoginView(viewManager, loginViewModel, dataAccess);


        // FOR TESTING THE TEAM VIEW
        viewManager.addView(loginView, loginViewModel.getName());
        viewManager.addView(signupView, signupViewModel.getName());
        viewManager.addView(mainMenuView, mainMenuViewModel.getName());
        viewManager.addView(optionsView, optionsViewModel.getName());
        viewManager.addView(userView, userViewModel.getName());
        viewManager.addView(gameView, gameViewModel.getName());
        viewManager.addView(profileView, profileViewModel.getName());
        viewManager.switchToView(loginViewModel.getName());
        viewManager.setResolution(loginViewModel.DEFAULT_SIZE);

        applicationFrame.setLocationRelativeTo(null);
        applicationFrame.setVisible(true);
        applicationFrame.setResizable(false);
    }
}
