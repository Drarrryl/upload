package Factory;

import data_access.DataAccessInterface;
import interface_adapter.MainMenu.MainMenuController;
import interface_adapter.MainMenu.MainMenuPresenter;
import interface_adapter.MainMenu.MainMenuViewModel;
import interface_adapter.Options.OptionsController;
import interface_adapter.Options.OptionsPresenter;
import interface_adapter.Options.OptionsViewModel;
import use_case.MainMenu.MainMenuInteractor;
import use_case.MainMenu.MainMenuOutputBoundary;
import use_case.Options.OptionsOutputBoundary;
import view.MainMenu.MainMenuView;
import view.Options.OptionsView;
import view.ViewManager;

public class OptionsFactory {
    public static OptionsView createOptionsView(ViewManager viewManager,
                                             OptionsViewModel optionsViewModel,
                                             DataAccessInterface userDataAccess)
    {
        OptionsController optionsController = createOptionsController(viewManager, optionsViewModel, userDataAccess);
        return new OptionsView(optionsViewModel, optionsController);
    }

    public static OptionsController createOptionsController(ViewManager viewManager,
                                                              OptionsViewModel optionsViewModel,
                                                              DataAccessInterface userDataAccess)
    {
        OptionsOutputBoundary presenter = new OptionsPresenter(viewManager, optionsViewModel);

        return new OptionsController(presenter);
    }
}
