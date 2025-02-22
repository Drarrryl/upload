package interface_adapter.Signup;

import interface_adapter.State;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SignupState implements State {
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    String username = "";
    String password = "";
    String repeatPassword = "";
    String usernameError = null;
    String passwordError = null;

    String creationTime = "";



    public SignupState() {}

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

    public String getRepeatPassword()
    {
        return repeatPassword;
    }

    public String getUsernameError()
    {
        return usernameError;
    }

    public String getPasswordError()
    {
        return passwordError;
    }

    public String getCreationTime()
    {
        return creationTime;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setRepeatPassword(String repeatPassword)
    {
        this.repeatPassword = repeatPassword;
    }

    public void setUsernameError(String error)
    {
        usernameError = error;
    }

    public void setPasswordError(String error)
    {
        passwordError = error;
    }

    public void setCreationTime(String time)
    {
        creationTime = time;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

}
