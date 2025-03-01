package interface_adapter.Signup;

import use_case.Signup.SignupInputBoundary;
import use_case.Signup.SignupInputData;

public class SignupController {

    private final SignupInputBoundary signupInputBoundary;

    public SignupController(SignupInputBoundary signupInputBoundary)
    {
        this.signupInputBoundary = signupInputBoundary;
    }

    public void execute(String username, String password, String repeatPassword)
    {
        SignupInputData input = new SignupInputData(username, password, repeatPassword);
        signupInputBoundary.execute(input);
    }
}
