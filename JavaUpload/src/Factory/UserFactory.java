package Factory;

import data_access.DataAccessInterface;
import entity.User;
import interface_adapter.user.UserController;
import interface_adapter.user.UserPresenter;
import interface_adapter.user.UserViewModel;
import use_case.User.UserInteractor;
import use_case.User.UserOutputBoundary;
import view.User.UserView;
import view.ViewManager;

public class UserFactory {
    public User create(String name, String password) {
        return new User(name, password);
    }

    public static UserView createUserView(ViewManager viewManager,
                                          UserViewModel userViewModel,
                                          DataAccessInterface userDataAccess)
    {
        UserController userController = createUserController(viewManager, userViewModel, userDataAccess);
        return new UserView(userViewModel, userController);
    }

    public static UserController createUserController(ViewManager viewManager,
                                                      UserViewModel userViewModel,
                                                      DataAccessInterface userDataAccess)
    {
        UserOutputBoundary presenter = new UserPresenter(viewManager, userViewModel);
        UserInteractor createInteractor = new UserInteractor(userDataAccess, presenter);

        return new UserController(createInteractor);
    }
}