package interface_adapter.Login;

import interface_adapter.State;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginState implements State {
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    String username = "";
    String password = "";
    String usernameError = null;
    String passwordError = null;

    public LoginState() {}

    public String getUsername()
    {
        return username;
    }

    @Override
    public long getHighscore() {
        return 0;
    }

    public String getPassword()
    {
        return password;
    }

    public String getUsernameError()
    {
        return usernameError;
    }

    public String getPasswordError()
    {
        return passwordError;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setUsernameError(String usernameError)
    {
        this.usernameError = usernameError;
    }

    public void setPasswordError(String passwordError)
    {
        this.passwordError = passwordError;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

}
