package interface_adapter.MainMenu;

import interface_adapter.State;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MainMenuState implements State {
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    String buttonName = "";
    String username = "";

    public MainMenuState() {}

    public String getButtonName() { return buttonName; }

    public void setButtonName(String buttonName) { this.buttonName = buttonName; }

    public String getUsername()
    {
        return username;
    }

    @Override
    public long getHighscore() {
        return 0;
    }

    public void setUsername(String newUsername)
    {
        String oldUsername = this.username;
        this.username = username;
        this.pcs.firePropertyChange("username", oldUsername, newUsername);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }
}
