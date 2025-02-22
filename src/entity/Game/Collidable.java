package entity.Game;

import interface_adapter.Game.GameController;

import java.awt.*;

public interface Collidable {
    public Rectangle getBounds();
    public void tick(Canvas canvas, GameController controller);
    public void render(Graphics g);
}
