package interface_adapter.Profile;

import entity.User;
import interface_adapter.State;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ProfileState implements State {
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private User loggedInUser;
    private String username;
    private long highscore;
    private boolean pfpStatus;

    public ProfileState() {

    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public long getHighscore() {
        return highscore;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setHighscore(long score) {
        this.highscore = score;
    }

    public void setStatus(boolean status) {
        boolean oldStatus = this.pfpStatus;
        this.pfpStatus = status;
        pcs.firePropertyChange("status", oldStatus, status);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }
}
