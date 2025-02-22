package interface_adapter.user;

import interface_adapter.State;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class UserState implements State {
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private String username;

    private long highscore;

    private boolean pfpStatus;

    public UserState() {}

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getHighscore() {
        return highscore;
    }

    public void setHighscore(long highscore) {
        this.highscore = highscore;
    }

    public boolean getPfpStatus() {
        return pfpStatus;
    }

    public void setPfpStatus(boolean pfpStatus) {
        boolean oldStatus = this.pfpStatus;
        this.pfpStatus = pfpStatus;
        pcs.firePropertyChange("status", oldStatus, pfpStatus);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }
}
