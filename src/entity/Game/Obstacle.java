package entity.Game;

import interface_adapter.Game.GameController;
import interface_adapter.Game.Physics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Obstacle extends GameObject implements Collidable {
    private String name;
    private BufferedImage idle;

    private double velY;

    public boolean grounded;
    private BufferedImage enemyJump1;
    private boolean jump1State;
    private BufferedImage enemyJump2;
    private boolean jump2State;

    private int jumpTimer = -1;
    private int jumpInitCooldown = 150;
    private int jumpCooldown = jumpInitCooldown;
    private float jumpAccel = 1.0f;
    private float jumpInitVel = 15.0f;
    private float jumpVel;
    private boolean isJumping;
    private boolean canJump = true;

    public Obstacle(double x, double y, BufferedImage[] obstacles, String name) {
        super(x, y, obstacles[0].getWidth(), obstacles[0].getHeight());

        this.name = name;
        this.idle = obstacles[0];

        if (name.equals("Enemy")) {
            enemyJump1 = obstacles[1];
            enemyJump2 = obstacles[2];
        }
    }

    @Override
    public void tick(Canvas canvas, GameController controller) {
        if (name.equals("Enemy")) {
            setY(getY() + velY);

            updateJump();
            updateJumpTimer();
            updateCooldown();
            toggleJump();

            gravity();
            checkGround(controller.getCollidableObjs());
            resetPos(canvas, controller.getCollidableObjs());
        }
    }

    @Override
    public void render(Graphics g) {
        if (name.equals("Enemy")) {
            if (jump1State) {
                setWidth(enemyJump1.getWidth());
                setHeight(enemyJump1.getHeight());
                g.drawImage(enemyJump1, (int) getX(), (int) getY(), null);
            } else if (jump2State) {
                setWidth(enemyJump2.getWidth());
                setHeight(enemyJump2.getHeight());
                g.drawImage(enemyJump2, (int) getX(), (int) getY(), null);
            } else {
                setWidth(idle.getWidth());
                setHeight(idle.getHeight());
                g.drawImage(idle, (int) getX(), (int) getY(), null);
            }
        } else {
            g.drawImage(idle, (int) getX(), (int) getY(), null);
        }
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public void gravity() {
        if (!grounded) {
            setVelY(velY + 0.15);
        }
    }

    public void checkGround(ArrayList<Collidable> collidables) {
        ArrayList<Collidable> tiles = new ArrayList<>();

        for (int i = 0; i < collidables.size(); i++) {
            if (collidables.get(i) instanceof TileObject) {
                tiles.add(collidables.get(i));
            }
        }
        grounded = Physics.Collision(this, tiles) != null;
    }

    public void resetPos(Canvas canvas, ArrayList<Collidable> collidables) {
        int yOffset = canvas.getHeight() - this.idle.getHeight();

        GameObject ground = Physics.Collision(this, collidables);

        if (ground != null) {
            yOffset = ((int) ground.getY() - this.idle.getHeight()) + 5;
        }

        if (grounded) setY(yOffset);
    }

    public void jump() {
        if (!jump1State && !jump2State && canJump) {
            canJump = false;
            jumpTimer = 6;
            jumpCooldown = jumpInitCooldown;
        }
    }

    public void updateJump() {
        if (isJumping) {
            setVelY(-this.jumpVel);
            this.jumpVel -= this.jumpAccel;

            if (this.jumpVel <= 0) {
                isJumping = false;
                this.jumpVel = this.jumpInitVel;
                setVelY(0);
            }
        }
    }

    public void updateJumpTimer() {
        if (jumpTimer >= 0) {
            jumpTimer -= 1;
        }

        if (jumpTimer < 0) {
            jump1State = false;
            jump2State = false;
        } else if (jumpTimer == 0) {
            jump1State = false;
            jump2State = false;
            isJumping = true;
        } else if (jumpTimer < 3) {
            jump2State = true;
            jump1State = false;
        } else {
            jump1State = true;
            jump2State = false;
        }
    }

    public void canJump() {
        canJump = true;
    }

    public void updateCooldown() {
        jumpCooldown -= 1;
        if (jumpCooldown <= 0) {
            canJump();
        }
    }

    public void toggleJump() {
        if (canJump) {
            jump();
        }
    }
}
