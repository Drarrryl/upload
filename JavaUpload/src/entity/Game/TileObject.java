package entity.Game;

import interface_adapter.Game.GameController;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class TileObject extends GameObject implements Collidable {

    private double velX = -3.0;

    private final BufferedImage tile;
    private final Obstacle obstacle;

    public boolean delete = false;
    public TileObject(double x, double y, HashMap<String, BufferedImage> tileSprites, String obstacle) {
        super(x, y, tileSprites.get("Grass").getWidth(), tileSprites.get("Grass").getHeight());

        tile = tileSprites.get("Grass");

        if (obstacle.equals("Spikes")) {
            BufferedImage[] obst = new BufferedImage[]{tileSprites.get(obstacle)};
            this.obstacle = new Obstacle(x, y - obst[0].getHeight(), obst, obstacle);
        } else if (obstacle.equals("Enemy")) {
            BufferedImage[] obst = new BufferedImage[3];
            obst[0] = tileSprites.get("Enemy");
            obst[1] = tileSprites.get("EnemyJump1");
            obst[2] = tileSprites.get("EnemyJump2");
            this.obstacle = new Obstacle(x, y - obst[0].getHeight(), obst, obstacle);
        } else {
            this.obstacle = null;
        }


    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    @Override
    public void tick(Canvas gameCanvas, GameController controller) {
        setX(getX() + velX);
        if (obstacle != null) {
            obstacle.setX((getX() + velX));
            obstacle.tick(gameCanvas, controller);
        }


        checkTilePos();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(tile, (int) getX(), (int) getY(), null);
        if (obstacle != null) {
            obstacle.render(g);
        }
    }

    public void checkTilePos() {
        if (getX() < -tile.getWidth()) {
            delete = true;
        }
    }

    public Obstacle getObstacle() {
        return obstacle;
    }
}
