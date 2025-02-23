package Factory;

import data_access.DataAccessInterface;
import interface_adapter.Signup.SignupController;
import interface_adapter.Signup.SignupPresenter;
import interface_adapter.Signup.SignupViewModel;
import use_case.Signup.SignupInteractor;
import use_case.Signup.SignupOutputBoundary;
import view.ViewManager;
import view.Signup.SignupView;

public class SignupFactory {

    public static SignupView createSignupView(ViewManager viewManager,
                                             SignupViewModel signupViewModel,
                                             DataAccessInterface userDataAccess,
                                             UserFactory userFactory)
    {
        SignupController controller = createSignupController(viewManager, signupViewModel, userDataAccess, userFactory);
        return new SignupView(signupViewModel, controller);
    }

    private static SignupController createSignupController(ViewManager viewManager,
                                                         SignupViewModel signupViewModel,
                                                         DataAccessInterface userDataAccess,
                                                         UserFactory userFactory)
    {
        SignupOutputBoundary presenter = new SignupPresenter(viewManager, signupViewModel);
        SignupInteractor interactor = new SignupInteractor(userDataAccess, presenter, userFactory);
        return new SignupController(interactor);
    }
}
