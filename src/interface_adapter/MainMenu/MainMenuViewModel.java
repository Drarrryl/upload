package interface_adapter.MainMenu;

import entity.User;
import interface_adapter.user.UserViewModel;
import view.ViewManager;
import view.ViewModel;

public class MainMenuViewModel extends ViewModel {

    public static final String START_BUTTON_STRING = "Start";
    public static final String EXIT_BUTTON_STRING = "Exit";

    private UserViewModel userViewModel;

    private MainMenuState mainMenuState;

    private User loggedInUser;

    public MainMenuViewModel(ViewManager viewManager, UserViewModel userViewModel)
    {
        super("Main Menu", viewManager);
        this.userViewModel = userViewModel;
        this.mainMenuState = new MainMenuState();
        this.loggedInUser = null;
    }

    public UserViewModel getUserViewModel() { return userViewModel; }

    public void setUserViewModel(UserViewModel userViewModel) { this.userViewModel = userViewModel; }

    public MainMenuState getState()
    {
        return mainMenuState;
    }

    public void setState(MainMenuState state)
    {
        this.mainMenuState = state;
    }

    public User getLoggedInUser() { return loggedInUser; }

    public void setLoggedInUser(User loggedInUser)
    {
        this.loggedInUser = loggedInUser;
    }
}
