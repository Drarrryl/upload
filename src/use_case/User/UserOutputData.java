package use_case.User;

import entity.User;

public class UserOutputData {

    private final User user;

    private long highscore;

    private boolean useCaseFailed;

    public UserOutputData(User user, long highscore) {
        this.user = user;
        this.highscore = highscore;
    }

    public User getUser() {
        return user;
    }

    public long getHighscore() {
        return highscore;
    }

}
