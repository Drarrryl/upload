package interface_adapter.Login;

import use_case.Login.LoginInputBoundary;
import use_case.Login.LoginInputData;

public class LoginController {

    final LoginInputBoundary loginInteractor;

    public LoginController(LoginInputBoundary loginInteractor)
    {
        this.loginInteractor = loginInteractor;
    }

    public void execute(String username, String password)
    {
        LoginInputData input = new LoginInputData(username, password);
        loginInteractor.execute(input);
    }

}
