package use_case.MainMenu;

import entity.User;

public class MainMenuOutputData {
    private final String username;
    private final User user;
    private boolean useCaseFailed;

    public MainMenuOutputData(String username, User user, boolean useCaseFailed) {
        this.username = username;
        this.user = user;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public User getUser() { return user; }
}
