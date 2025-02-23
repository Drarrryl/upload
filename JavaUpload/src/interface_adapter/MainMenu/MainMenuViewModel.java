package interface_adapter.MainMenu;

import entity.User;
import interface_adapter.Options.OptionsViewModel;
import interface_adapter.user.UserViewModel;
import view.ViewManager;
import view.ViewModel;

import java.awt.*;

public class MainMenuViewModel extends ViewModel {

    public static final String TITLE_TEXT_STRING = "New Software";
    public static final String LOGGED_IN_AS_STRING = "Logged in as: ";
    public static final String START_BUTTON_STRING = "Start";
    public static final String OPTIONS_BUTTON_STRING = "Options";
    public static final String EXIT_BUTTON_STRING = "Exit";

    private UserViewModel userViewModel;

    private OptionsViewModel optionsViewModel;

    private MainMenuState state = new MainMenuState();

    private User loggedInUser;

    public MainMenuViewModel(ViewManager viewManager, OptionsViewModel optionsViewModel, UserViewModel userViewModel)
    {
        super("Main Menu", viewManager);
        this.userViewModel = userViewModel;
        this.optionsViewModel = optionsViewModel;
        this.loggedInUser = null;
    }

    public UserViewModel getUserViewModel() { return userViewModel; }

    public void setUserViewModel(UserViewModel userViewModel) { this.userViewModel = userViewModel; }

    public OptionsViewModel getOptionsViewModel() { return optionsViewModel; }

    public void setOptionsViewModel(OptionsViewModel optionsViewModel) { this.optionsViewModel = optionsViewModel; }

    public MainMenuState getState()
    {
        return state;
    }

    public void setState(MainMenuState state)
    {
        this.state = state;
    }

    public User getLoggedInUser() { return loggedInUser; }

    public void setLoggedInUser(User loggedInUser)
    {
        this.loggedInUser = loggedInUser;
    }
}
