package interface_adapter.Game;

import entity.Game.*;
import entity.User;
import org.checkerframework.checker.units.qual.A;
import use_case.Game.GameInputBoundary;
import use_case.Game.GameInputData;
import use_case.Game.GameOutputBoundary;
import use_case.Game.GameOutputData;
import use_case.User.UserInputBoundary;
import use_case.User.UserInputData;
import view.Game.GameView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class GameController {

    private boolean a_pressed = false;
    private boolean d_pressed = false;

    private ArrayList<Collidable> collidableObjs = new ArrayList<>();
    private ArrayList<NonCollidable> nonCollidableObjs = new ArrayList<>();
    private final GameInputBoundary gameInteractor;

    public GameController(GameInputBoundary gameInputBoundary)
    {
        this.gameInteractor = gameInputBoundary;
    }

    public void keyPressed(int keyCode, Player plr) {
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            if (plr.grounded) plr.jump();
        }
        if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
            if (!plr.grounded) plr.setVelY(5);
        }
        if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            a_pressed = true;
            plr.setVelX(-plr.speed);
        }
        if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            d_pressed = true;
            plr.setVelX(plr.speed);
        }
    }

    public void keyReleased(int keyCode, Player plr) {
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            plr.canJump();
        }
        if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            a_pressed = false;
            if (!d_pressed) plr.setVelX(0);
        } else if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            d_pressed = false;
            if (!a_pressed) plr.setVelX(0);
        }
    }

    public void addObj(Collidable obj) {
        collidableObjs.add(obj);
    }

    public void removeObj(Collidable obj) {
        collidableObjs.remove(obj);
    }

    public void addObj(NonCollidable obj) {
        nonCollidableObjs.add(obj);
    }

    public void removeObj(NonCollidable obj) {
        nonCollidableObjs.remove(obj);
    }

    public void tick(Canvas gameCanvas, GameState gameState) {
        for (Collidable obj : collidableObjs) {
            obj.tick(gameCanvas, this);
        }

        for (NonCollidable obj : nonCollidableObjs) {
            obj.tick(gameCanvas, this);
        }

        updateTile(gameState);
    }

    public void render(Graphics g) {
        for (Collidable obj : collidableObjs) {
            obj.render(g);
        }

        for (NonCollidable obj : nonCollidableObjs) {
            obj.render(g);
        }
    }

    public ArrayList<Collidable> getCollidableObjs() {
        return collidableObjs;
    }

    public ArrayList<NonCollidable> getNonCollidableObjs() {
        return nonCollidableObjs;
    }

    public void resetObjs() {
        this.collidableObjs = new ArrayList<Collidable>();
        this.nonCollidableObjs = new ArrayList<NonCollidable>();
    }

    public void updateTile(GameState gameState) {
        long score = gameState.getScore();
        HashMap<String, BufferedImage> tileSprites = gameState.getTileSprites();
        for (int i = 0; i < collidableObjs.size(); i++) {
            Collidable currObj = collidableObjs.get(i);

            if (currObj instanceof TileObject) {
                if (((TileObject) currObj).delete) {
                    removeObj(currObj);
                    TileObject tailTile = findTailTile();
                    double x = tailTile.getX() + tailTile.getWidth();
                    double y = tailTile.getY();
                    Random r = new Random();
                    String[] choices1 = {"Spikes", "", "", "", ""};
                    String[] choices2 = {"Spikes", "Enemy", "", "", ""};
                    String[] choices3 = {"Spikes", "Enemy", "Spikes", "", ""};
                    String[] choices4 = {"Enemy", "Spikes", "Enemy", "Spikes", ""};

                    TileObject newTile;

                    int count = 0;
                    boolean overrideObstacle = false;

                    for (TileObject obj : find3TailTiles()) {
                        if (obj.getObstacle() != null) count++;
                    }

                    if (count == 3) {
                        overrideObstacle = true;
                    }

                    if (overrideObstacle) {
                        newTile = new TileObject(x, y, tileSprites, "");
                    } else if (score < 1000) {
                        newTile = new TileObject(x, y, tileSprites, choices1[r.nextInt(0, 5)]);
                    } else if (score < 2000) {
                        newTile = new TileObject(x, y, tileSprites, choices2[r.nextInt(0, 5)]);
                    } else if (score < 3000) {
                        newTile = new TileObject(x, y, tileSprites, choices3[r.nextInt(0, 5)]);
                    } else {
                        newTile = new TileObject(x, y, tileSprites, choices4[r.nextInt(0, 5)]);
                    }

                    addObj(newTile);
                    if (newTile.getObstacle() != null) {
                        addObj(newTile.getObstacle());
                    }
                }
            }
        }
    }

    public void updatePlr(Player plr, GameState gameState) {
        ArrayList<GameObject> objs = Physics.Collisions(plr, collidableObjs);

        if (!objs.isEmpty()) {
            for (GameObject gameObj : objs) {
                if (gameObj instanceof Obstacle) {
                    gameState.setPhase("pause");
                }
            }
        }
    }

    private TileObject findTailTile() {
        TileObject resultObj = null;

        for (int i = 0; i < collidableObjs.size(); i++) {
            Collidable currObj = collidableObjs.get(i);

            if (currObj instanceof TileObject) {
                if (resultObj == null) {
                    resultObj = (TileObject) currObj;
                } else if (((TileObject) currObj).getX() > resultObj.getX()) {
                    resultObj = (TileObject) currObj;
                }
            }
        }
        return resultObj;
    }

    private TileObject findTailTileHelper(ArrayList<Collidable> collidables) {
        TileObject resultObj = null;

        for (int i = 0; i < collidables.size(); i++) {
            Collidable currObj = collidables.get(i);

            if (currObj instanceof TileObject) {
                if (resultObj == null) {
                    resultObj = (TileObject) currObj;
                } else if (((TileObject) currObj).getX() > resultObj.getX()) {
                    resultObj = (TileObject) currObj;
                }
            }
        }
        return resultObj;
    }

    private TileObject[] find3TailTiles() {
        ArrayList<Collidable> objs = (ArrayList<Collidable>) collidableObjs.clone();

        TileObject[] result = new TileObject[3];

        TileObject obj1 = findTailTileHelper(objs);
        result[0] = obj1;
        objs.remove(obj1);
        TileObject obj2 = findTailTileHelper(objs);
        result[1] = obj2;
        objs.remove(obj2);
        TileObject obj3 = findTailTileHelper(objs);
        result[2] = obj3;

        return result;
    }

    public void saveScore(long score, User user) {
        GameInputData data = new GameInputData(score, user);
        gameInteractor.save(data);
    }

    public void exitGame(long score, User user) {
        GameInputData data = new GameInputData(score, user);
        gameInteractor.saveAndQuit(data);
    }
}
