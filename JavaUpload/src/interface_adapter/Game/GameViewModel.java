package interface_adapter.Game;

import entity.User;
import view.ViewManager;
import view.ViewModel;

public class GameViewModel extends ViewModel {

    private User loggedInUser;

    public String TITLE = "Placeholder Game";
    public String START_GAME_BUTTON_STRING = "Start Game";
    public String PLAY_AGAIN_BUTTON_STRING = "Play Again";
    public String EXIT_GAME_BUTTON_STRING = "Exit Game";

    private GameState state = new GameState();

    public GameViewModel(ViewManager viewManager)
    {
        super("Game", viewManager);

        loggedInUser = null;
    }

    public GameState getState()
    {
        return state;
    }

    public void setState(GameState state)
    {
        this.state = state;
    }


    public User getLoggedInUser()
    {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser)
    {
        this.loggedInUser = loggedInUser;
    }

}
