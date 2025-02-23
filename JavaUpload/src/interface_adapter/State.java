package interface_adapter;

import java.beans.PropertyChangeListener;

public interface State {
    public String getUsername();
    public long getHighscore();
    public void addPropertyChangeListener(PropertyChangeListener listener);
    public void removePropertyChangeListener(PropertyChangeListener listener);
}
