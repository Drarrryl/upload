package entity.Game;

import interface_adapter.Game.GameController;
import interface_adapter.Game.Physics;
import view.Game.GameView;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;

public class Player extends GameObject implements NonCollidable {

    private double velX = 0;
    private double velY = 0;

    public double speed;

    public boolean grounded;

    private BufferedImage idle;
    private BufferedImage jump1;
    private boolean jump1State;
    private BufferedImage jump2;
    private boolean jump2State;

    private int jumpTimer = -1;
    private float jumpAccel = 1.0f;
    private float jumpInitVel = 15.0f;
    private float jumpVel;
    private boolean isJumping;
    private boolean canJump = true;

    public Player(double x, double y, HashMap<String, BufferedImage> playerSprites) {
        super(x, y, playerSprites.get("Idle").getWidth(), playerSprites.get("Idle").getHeight());

        speed = 5;

        idle = playerSprites.get("Idle");
        jump1 = playerSprites.get("Jump 1");
        jump2 = playerSprites.get("Jump 2");

        this.jumpVel = jumpInitVel;
    }

    @Override
    public void tick(Canvas gameCanvas, GameController controller) {
        setX(getX() + velX);
        setY(getY() + velY);

        updateJumpTimer();
        updateJump();

        gravity();
        checkGround(controller.getCollidableObjs());
        resetPos(gameCanvas, controller.getCollidableObjs());
    }

    @Override
    public void render(Graphics g) {
        if (jump1State) {
            setWidth(jump1.getWidth());
            setHeight(jump1.getHeight());
            g.drawImage(jump1, (int) getX(), (int) getY(), null);
        } else if (jump2State) {
            setWidth(jump2.getWidth());
            setHeight(jump2.getHeight());
            g.drawImage(jump2, (int) getX(), (int) getY(), null);
        } else {
            setWidth(idle.getWidth());
            setHeight(idle.getHeight());
            g.drawImage(idle, (int) getX(), (int) getY(), null);
        }
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public void gravity() {
        if (!grounded) {
            setVelY(velY + 0.5);
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
        int xOffset = canvas.getWidth() - this.idle.getWidth();
        int yOffset = canvas.getHeight() - this.idle.getHeight();

        GameObject ground = Physics.Collision(this, collidables);

        if (ground != null) {
            yOffset = ((int) ground.getY() - this.idle.getHeight()) + 5;
        }

        if (getX() < 0) setX(0);
        if (getX() > xOffset) setX(xOffset);

        if (getY() < 0) setY(0);
        if (grounded) setY(yOffset);
    }

    public void jump() {
        if (!jump1State && !jump2State && canJump) {
            canJump = false;
            jumpTimer = 4;
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
        } else if (jumpTimer < 2) {
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
}
