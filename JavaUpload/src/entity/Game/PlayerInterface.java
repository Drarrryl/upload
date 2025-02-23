package entity.Game;

import java.awt.*;

public interface PlayerInterface {
    public void tick(Canvas canvas);
    public void render(Graphics g);
    public double getX();
    public void setX(double x);
    public double getY();
    public void setY(double y);
}
