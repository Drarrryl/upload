package entity.Game;

import java.awt.*;

public abstract class GameObject implements GameObjectInterface {

    private double x;
    private double y;
    private int width;
    private int height;

    public GameObject(double x, double y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void tick(Canvas gameCanvas) {

    }

    @Override
    public void render(Graphics g) {

    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }
}
