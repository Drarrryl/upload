package use_case.MainMenu;

import entity.User;

public class MainMenuOutputData {
    private final String username;
    private final long highscore;
    private final User user;

    public MainMenuOutputData(String username, long highscore, User user) {
        this.username = username;
        this.highscore = highscore;
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public User getUser() { return user; }

    public long getHighscore() {
        return highscore;
    }
}
