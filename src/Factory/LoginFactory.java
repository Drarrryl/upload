package Factory;

import data_access.DataAccessInterface;
import interface_adapter.Login.LoginController;
import interface_adapter.Login.LoginPresenter;
import interface_adapter.Login.LoginViewModel;
import use_case.Login.LoginInteractor;
import use_case.Login.LoginOutputBoundary;
import view.ViewManager;
import view.Login.LoginView;

public class LoginFactory {

    public static LoginView createLoginView(ViewManager viewManager,
                                     LoginViewModel loginViewModel,
                                     DataAccessInterface userDataAccess)
    {
        LoginController controller = createLoginController(viewManager, loginViewModel, userDataAccess);
        return new LoginView(loginViewModel, controller);
    }

    private static LoginController createLoginController(ViewManager viewManager,
                                                 LoginViewModel loginViewModel,
                                                 DataAccessInterface userDataAccess)
    {
        LoginOutputBoundary presenter = new LoginPresenter(viewManager, loginViewModel);
        LoginInteractor interactor = new LoginInteractor(userDataAccess, presenter);
        return new LoginController(interactor);
    }

}
