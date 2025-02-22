package Factory;

import data_access.DataAccessInterface;
import entity.User;
import interface_adapter.MainMenu.MainMenuController;
import interface_adapter.MainMenu.MainMenuPresenter;
import interface_adapter.MainMenu.MainMenuViewModel;
import interface_adapter.user.UserController;
import interface_adapter.user.UserPresenter;
import interface_adapter.user.UserViewModel;
import use_case.MainMenu.MainMenuInteractor;
import use_case.MainMenu.MainMenuOutputBoundary;
import use_case.User.UserInteractor;
import use_case.User.UserOutputBoundary;
import view.MainMenu.MainMenuView;
import view.User.UserView;
import view.ViewManager;

public class MainMenuFactory {

    public static MainMenuView createUserView(ViewManager viewManager,
                                          MainMenuViewModel mainMenuViewModel,
                                          DataAccessInterface userDataAccess)
    {
        MainMenuController mainMenuController = createMainMenuController(viewManager, mainMenuViewModel, userDataAccess);
        return new MainMenuView(mainMenuViewModel, mainMenuController);
    }

    public static MainMenuController createMainMenuController(ViewManager viewManager,
                                                              MainMenuViewModel mainMenuViewModel,
                                                              DataAccessInterface userDataAccess)
    {
        MainMenuOutputBoundary presenter = new MainMenuPresenter(viewManager, mainMenuViewModel);
        MainMenuInteractor createInteractor = new MainMenuInteractor(userDataAccess, presenter);

        return new MainMenuController(createInteractor);
    }
}