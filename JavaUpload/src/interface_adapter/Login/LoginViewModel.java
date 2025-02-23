package interface_adapter.Login;

import entity.User;
import interface_adapter.MainMenu.MainMenuViewModel;
import interface_adapter.Signup.SignupViewModel;
import interface_adapter.user.UserViewModel;
import view.ViewManager;
import view.ViewModel;

import java.awt.*;

public class LoginViewModel extends ViewModel {

    public static Color BACKGROUND_COLOR = Color.WHITE;
    public static Color TEXT_COLOR = Color.BLACK;

    public static final String LOGIN_BUTTON_STRING = "Login";
    public static final String SIGNUP_BUTTON_STRING = "Sign Up";
    public static final String USERNAME_FIELD_STRING = "Username: ";
    public static final String PASSWORD_FIELD_STRING = "Password: ";

    private SignupViewModel signupViewModel;

    private MainMenuViewModel mainMenuViewModel;

    private LoginState state = new LoginState();

    public LoginViewModel(ViewManager viewManager, SignupViewModel signupViewModel, MainMenuViewModel mainMenuViewModel)
    {
        super("Login", viewManager);
        this.signupViewModel = signupViewModel;
        this.mainMenuViewModel = mainMenuViewModel;
        this.bg_color = BACKGROUND_COLOR;
        this.txt_color = TEXT_COLOR;
        this.DEFAULT_SIZE = new Dimension(WINDOW_DEFAULT_SIZE.width/4, WINDOW_DEFAULT_SIZE.height/4);
    }

    public SignupViewModel getSignupViewModel()
    {
        return signupViewModel;
    }

    public void setSignupViewModel(SignupViewModel signupViewModel)
    {
        this.signupViewModel = signupViewModel;
    }

    public MainMenuViewModel getMainMenuViewModel()
    {
        return mainMenuViewModel;
    }

    public void setMainMenuViewModel(MainMenuViewModel mainMenuViewModel)
    {
        this.mainMenuViewModel = mainMenuViewModel;
    }

    public LoginState getState()
    {
        return state;
    }

    public void setState(LoginState state)
    {
        this.state = state;
    }
}
