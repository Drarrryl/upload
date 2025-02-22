package interface_adapter.Login;

import entity.User;
import interface_adapter.MainMenu.MainMenuViewModel;
import interface_adapter.Signup.SignupViewModel;
import interface_adapter.user.UserViewModel;
import view.ViewManager;
import view.ViewModel;

public class LoginViewModel extends ViewModel {

    public static final String LOGIN_BUTTON_STRING = "Login";
    public static final String SIGNUP_BUTTON_STRING = "Sign Up";
    public static final String USERNAME_FIELD_STRING = "Username: ";
    public static final String PASSWORD_FIELD_STRING = "Password: ";

    private SignupViewModel signupViewModel;

    private MainMenuViewModel mainMenuViewModel;

    private LoginState loginState;

    public LoginViewModel(ViewManager viewManager, SignupViewModel signupViewModel, MainMenuViewModel mainMenuViewModel)
    {
        super("Login", viewManager);
        this.signupViewModel = signupViewModel;
        this.mainMenuViewModel = mainMenuViewModel;
        this.loginState = new LoginState();
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
        return loginState;
    }

    public void setState(LoginState state)
    {
        this.loginState = state;
    }
}
