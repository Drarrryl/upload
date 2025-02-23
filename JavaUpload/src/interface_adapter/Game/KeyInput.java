package interface_adapter.Game;

import view.Game.GameView;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    GameView gameView;

    public KeyInput(GameView gameView) {
        this.gameView = gameView;
    }

    public void keyPressed(KeyEvent e) {
        gameView.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        gameView.keyReleased(e);
    }
}
