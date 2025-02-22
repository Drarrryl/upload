package entity.Game;

import interface_adapter.Game.GameController;

import java.awt.*;

public interface NonCollidable {
    public void tick(Canvas gameCanvas, GameController controller);
    public void render(Graphics g);
    public double getX();
    public void setX(double x);
    public double getY();
    public void setY(double y);
}
