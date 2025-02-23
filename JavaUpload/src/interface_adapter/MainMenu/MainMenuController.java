package interface_adapter.MainMenu;

import use_case.MainMenu.MainMenuInputBoundary;
import use_case.MainMenu.MainMenuInputData;

public class MainMenuController {
    final MainMenuInputBoundary mainMenuInteractor;

    public MainMenuController(MainMenuInputBoundary mainMenuInteractor)
    {
        this.mainMenuInteractor = mainMenuInteractor;
    }

    public void execute(String buttonName, String username)
    {
        MainMenuInputData input = new MainMenuInputData(buttonName, username);
        mainMenuInteractor.execute(input);
    }
}
