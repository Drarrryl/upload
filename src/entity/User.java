package entity;

public class User implements UserInterface {

    private final String username;
    private final String password;

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
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
}
