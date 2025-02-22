package entity;

public class User implements UserInterface {

    private final String username;
    private final String password;
    private long highscore;

    /**
     * Constructor for User()
     *
     * @param username   username of the User
     * @param password   password of the User
     *
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.highscore = 0;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public long getHighscore() {
        return highscore;
    }

    @Override
    public void setHighscore(long highscore) {
        this.highscore = highscore;
    }
}
