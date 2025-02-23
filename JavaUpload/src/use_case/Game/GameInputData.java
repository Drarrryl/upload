package use_case.Game;

import entity.User;

public class GameInputData {
    private long score;
    private User user;

    public GameInputData(long score, User user) {
        this.score = score;
        this.user = user;
    }

    public long getScore() {
        return score;
    }

    public User getUser() {
        return user;
    }
}
