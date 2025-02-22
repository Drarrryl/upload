package Factory;

import data_access.DataAccessInterface;
import interface_adapter.Game.GameController;
import interface_adapter.Game.GamePresenter;
import interface_adapter.Game.GameViewModel;
import use_case.Game.GameInputBoundary;
import use_case.Game.GameInteractor;
import use_case.Game.GameOutputBoundary;
import view.Game.GameView;
import view.ViewManager;

public class GameFactory {

    public static GameView createGameView(ViewManager viewManager,
                                          GameViewModel gameViewModel,
                                          DataAccessInterface dataAccessInterface) {
        GameController gameController = createGameController(viewManager, gameViewModel, dataAccessInterface);
        return new GameView(gameViewModel, gameController);

    }

    public static GameController createGameController(ViewManager viewManager,
                                                      GameViewModel gameViewModel,
                                                      DataAccessInterface dataAccessInterface) {
        GameOutputBoundary gamePresenter = new GamePresenter(viewManager, gameViewModel);
        GameInputBoundary gameInteractor = new GameInteractor(dataAccessInterface, gamePresenter);

        return new GameController(gameInteractor);
    }
}
