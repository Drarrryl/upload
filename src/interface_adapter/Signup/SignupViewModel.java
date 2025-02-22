package interface_adapter.Signup;

import view.ViewManager;
import view.ViewModel;

import java.awt.*;

public class SignupViewModel extends ViewModel {

    public static final String BACK_BUTTON_STRING = "Back";
    public static final String SIGNUP_BUTTON_STRING = "Sign Up";
    public static final String USERNAME_FIELD_STRING = "Username: ";
    public static final String PASSWORD_FIELD_STRING1 = "Password: ";
    public static final String PASSWORD_FIELD_STRING2 = "Confirm Password: ";

    private SignupState state;

    public SignupViewModel(ViewManager viewManager)
    {
        super("Signup", viewManager);
        this.DEFAULT_SIZE = new Dimension(WINDOW_DEFAULT_SIZE.width/4, WINDOW_DEFAULT_SIZE.height/4);
        this.state = new SignupState();
    }

    public SignupState getState()
    {
        return state;
    }

    public void setState(SignupState state)
    {
        this.state = state;
    }

}
