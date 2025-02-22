package interface_adapter.Game;

import interface_adapter.user.UserViewModel;
import use_case.Game.GameOutputBoundary;
import use_case.Game.GameOutputData;
import use_case.User.UserOutputBoundary;
import use_case.User.UserOutputData;
import view.ViewManager;

public class GamePresenter implements GameOutputBoundary
{
    ViewManager viewManager;
    GameViewModel gameViewModel;

    public GamePresenter(ViewManager viewManager, GameViewModel gameViewModel)
    {
        this.viewManager = viewManager;
        this.gameViewModel = gameViewModel;
    }

    @Override
    public void prepareWinView() {

    }

    @Override
    public void prepareLoseView() {

    }

    @Override
    public void prepareSaveAndQuit(GameOutputData outputData) {
        UserViewModel viewModel = (UserViewModel) viewManager.getLastViewModel();
        viewManager.switchToLastView();
        viewManager.setResolution(viewModel.DEFAULT_SIZE);
        viewModel.getState().setUsername(outputData.getUser().getUsername());
        viewModel.getState().setHighscore(outputData.getUser().getHighscore());
        viewModel.getState().setPfpStatus(true);
    }
}
