package app;

import Factory.LoginFactory;
import Factory.MainMenuFactory;
import Factory.SignupFactory;
import Factory.UserFactory;
import data_access.DataAccess;
import data_access.DataAccessInterface;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.MainMenu.MainMenuViewModel;
import interface_adapter.Signup.SignupViewModel;
import interface_adapter.user.UserViewModel;
import view.Login.LoginView;
import view.MainMenu.MainMenuView;
import view.Signup.SignupView;
import view.User.UserView;
import view.ViewManager;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        DataAccessInterface dataAccess = new DataAccess();

        JFrame applicationFrame = new JFrame("Team Task Manager");
        applicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ViewManager viewManager = new ViewManager(applicationFrame);

        SignupViewModel signupViewModel = new SignupViewModel(viewManager);
        SignupView signupView = SignupFactory.createSignupView(viewManager, signupViewModel, dataAccess, new UserFactory());

        UserViewModel userViewModel = new UserViewModel(viewManager);
        UserView userView = UserFactory.createUserView(viewManager, userViewModel, dataAccess);

        MainMenuViewModel mainMenuViewModel = new MainMenuViewModel(viewManager, userViewModel);
        MainMenuView mainMenuView = MainMenuFactory.createUserView(viewManager, mainMenuViewModel, dataAccess);

        LoginViewModel loginViewModel = new LoginViewModel(viewManager, signupViewModel, mainMenuViewModel);
        LoginView loginView = LoginFactory.createLoginView(viewManager, loginViewModel, dataAccess);

        // FOR TESTING THE TEAM VIEW
        viewManager.addView(loginView, loginViewModel.getName());
        viewManager.addView(signupView, signupViewModel.getName());
        viewManager.addView(mainMenuView, mainMenuViewModel.getName());
        viewManager.addView(userView, userViewModel.getName());
        viewManager.switchToView(loginViewModel.getName());

        applicationFrame.setLocationRelativeTo(null);
        applicationFrame.setVisible(true);
        applicationFrame.setResizable(false);
    }
}
