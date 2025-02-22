package interface_adapter.Game;

import entity.User;
import interface_adapter.State;

import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;

public class GameState implements State {
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private User user;
    private long highScore;
    private int numberOfAttempts;
    private boolean status;
    private long score;
    private String phase;

    private BufferedImage bgImage;
    private HashMap<String, BufferedImage> playerSprites;
    private HashMap<String, BufferedImage> tileSprites;

    public GameState() {}

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        boolean oldStatus = this.status;
        this.status = status;
        pcs.firePropertyChange("status", oldStatus, status);
    }

    public String getPhase() {
        return this.phase;
    }

    public void setPhase(String phase) {
        String oldPhase = this.phase;
        this.phase = phase;
        pcs.firePropertyChange("phase", oldPhase, phase);
    }

    public long getScore() {
        return this.score;
    }

    public void setScore(long score) {
        long oldScore = this.score;
        this.score = score;
        pcs.firePropertyChange("score", oldScore, score);
    }

    public long getHighScore() {
        return this.highScore;
    }

    public void setHighScore(long highScore) {
        this.highScore = highScore;
    }

    public BufferedImage getBgImage() {
        return bgImage;
    }

    public void setBgImage(BufferedImage bgImage) {
        this.bgImage = bgImage;
    }

    public HashMap<String, BufferedImage> getPlayerSprites() {
        return playerSprites;
    }

    public void setPlayerSprites(HashMap<String, BufferedImage> playerSprites) {
        this.playerSprites = playerSprites;
    }

    public HashMap<String, BufferedImage> getTileSprites() {
        return tileSprites;
    }

    public void setTileSprites(HashMap<String, BufferedImage> tileSprites) {
        this.tileSprites = tileSprites;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public long getHighscore() {
        return 0;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }
}
