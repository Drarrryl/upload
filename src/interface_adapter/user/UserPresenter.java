package interface_adapter.user;

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
    public void prepareSuccessView(UserOutputData user) {

    }

    @Override
    public void prepareFailView(String error) {

    }
}
