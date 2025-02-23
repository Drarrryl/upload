package use_case.Game;

import data_access.DataAccessInterface;
import entity.User;
import use_case.Game.GameOutputBoundary;

public class GameInteractor implements GameInputBoundary {
    final DataAccessInterface gameDataAccessObject;
    final GameOutputBoundary gamePresenter;
    public GameInteractor(DataAccessInterface gameDataAccessInterface,
                          GameOutputBoundary gameOutputBoundary) {
        this.gameDataAccessObject = gameDataAccessInterface;
        this.gamePresenter = gameOutputBoundary;
    }

    public void save(GameInputData inputData) {
        long score = inputData.getScore();
        User user = inputData.getUser();

        if (score > user.getHighscore()) {
            gameDataAccessObject.updateHighscore(user, score);
        }
    }

    public void saveAndQuit(GameInputData inputData) {
        save(inputData);

        long score = inputData.getScore();
        User user = inputData.getUser();

        GameOutputData outputData = new GameOutputData(score, user);
        gamePresenter.prepareSaveAndQuit(outputData);
    }
}
