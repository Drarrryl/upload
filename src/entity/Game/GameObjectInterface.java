package entity.Game;

import java.awt.*;

public interface GameObjectInterface {
    public double getX();
    public void setX(double x);
    public double getY();
    public void setY(double y);
    public void tick(Canvas gameCanvas);
    public void render(Graphics g);
}
