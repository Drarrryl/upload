package view.Game;

import entity.Game.Player;
import entity.Game.TileObject;
import interface_adapter.Game.GameController;
import interface_adapter.Game.GameState;
import interface_adapter.Game.GameViewModel;
import interface_adapter.Game.KeyInput;
import view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.HashMap;

public class GameView extends View implements Runnable {

    private GameController gameController;
    private GameState gameState;

    private Canvas gameCanvas;

    private Player plr;

    private final JLabel scoreLabel;

    private final JPanel buttonPanel;

    private final JButton startButton;

    private final JButton playAgainButton;

    private final JButton exitGameButton;

    private boolean running = false;
    private Thread thread;

    public GameView(GameViewModel viewModel, GameController gameController) {
        super(viewModel);
        this.gameController = gameController;
        this.gameState = viewModel.getState();

        float scale = 0.8f;
        final int WIDTH = (int) (viewModel.DEFAULT_SIZE.width * scale);
        final int HEIGHT = (int) (viewModel.DEFAULT_SIZE.height * scale);

        gameCanvas = new Canvas();
        gameCanvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        gameCanvas.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        gameCanvas.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        gameCanvas.addKeyListener(new KeyInput(this));

        BufferedImageLoader loader = new BufferedImageLoader();

        BufferedImage bgImage = null;
        HashMap<String, BufferedImage> tileSprites = new HashMap<>();
        HashMap<String, BufferedImage> playerSprites = new HashMap<>();

        try {
            bgImage = loader.loadImage("/images/bg.png");

            playerSprites.put("Idle", loader.loadImage("/images/idle.png"));
            playerSprites.put("Jump 1", loader.loadImage("/images/jump_1.png"));
            playerSprites.put("Jump 2", loader.loadImage("/images/jump_2.png"));

            tileSprites.put("Grass", loader.loadImage("/images/grass.png"));
            tileSprites.put("Spikes", loader.loadImage("/images/spikes.png"));
            tileSprites.put("Enemy", loader.loadImage("/images/enemy_idle.png"));
            tileSprites.put("EnemyJump1", loader.loadImage("/images/enemy_jump1.png"));
            tileSprites.put("EnemyJump2", loader.loadImage("/images/enemy_jump2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        gameState.setBgImage(bgImage);
        gameState.setPlayerSprites(playerSprites);
        gameState.setTileSprites(tileSprites);

        plr = new Player(200, 200, playerSprites);

        gameState.setScore(0);
        scoreLabel = new JLabel("Score: " + String.valueOf(gameState.getScore()));
        scoreLabel.setHorizontalTextPosition(SwingConstants.LEFT);

        viewModel.getViewManager().getApplicationFrame().setTitle(viewModel.TITLE);

        buttonPanel = new JPanel();
        startButton = new JButton(viewModel.START_GAME_BUTTON_STRING);
        playAgainButton = new JButton(viewModel.PLAY_AGAIN_BUTTON_STRING);
        exitGameButton = new JButton(viewModel.EXIT_GAME_BUTTON_STRING);
        buttonPanel.add(startButton);
        buttonPanel.add(playAgainButton);
        buttonPanel.add(exitGameButton);
        viewModel.SetTheme(buttonPanel, viewModel.getViewManager().getTheme());
        buttonPanel.remove(playAgainButton);
        buttonPanel.updateUI();

        exitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stop();
                gameState.setStatus(false);
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });

        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameState.setPhase("restart");

                gameController.saveScore(gameState.getScore(), viewModel.getLoggedInUser());

                buttonPanel.remove(playAgainButton);
                buttonPanel.updateUI();

                stop();
                init();
                start();
            }
        });

        gameState.addPropertyChangeListener(
                new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        if (evt.getPropertyName().equals("status")) {
                            if (evt.getNewValue().equals(true)) {
                                init();
                            }
                        }
                    }
        });

        gameState.addPropertyChangeListener(
                new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        if (evt.getPropertyName().equals("phase")) {
                            if (evt.getNewValue().equals("pause")) {
                                pause();
                            }
                        }
                    }
                }
        );

        this.add(scoreLabel);
        this.add(gameCanvas);
        this.add(buttonPanel);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        viewModel.getViewManager().getApplicationFrame().pack();
        viewModel.SetTheme(this, viewModel.getViewManager().getTheme());
    }

    private synchronized void init() {
        if (running) {
            return;
        }

        running = true;

        gameState.setPhase("init");

        gameState.setScore(0);
        if (((GameViewModel) getViewModel()).getLoggedInUser() != null) {
            scoreLabel.setText("Score: " + String.valueOf(gameState.getScore()) + " Highscore: " + ((GameViewModel) getViewModel()).getLoggedInUser().getHighscore());
        }

        plr = new Player(200, 200, gameState.getPlayerSprites());
        plr.setX(200);
        plr.setY(gameCanvas.getHeight()-plr.getHeight()-gameState.getTileSprites().get("Grass").getHeight()+5);
        plr.setVelX(0);
        plr.setVelY(0);

        gameController.addObj(plr);

        generateTiles(gameState.getTileSprites());

        thread = new Thread(this);
        thread.start();
    }

    private synchronized void start() {
        gameState.setPhase("start");

        gameState.setScore(0);

        buttonPanel.remove(startButton);
        buttonPanel.updateUI();

        gameCanvas.requestFocus();
        gameCanvas.update(gameCanvas.getGraphics());
    }

    private synchronized void pause() {
        buttonPanel.add(playAgainButton, 0);
        buttonPanel.updateUI();
        getViewModel().SetTheme(buttonPanel, getViewModel().getViewManager().getTheme());
    }

    private synchronized void stop() {
        if (!running) {
            return;
        }

        //phase[0] = "stop";

        buttonPanel.add(startButton, 0);
        buttonPanel.remove(playAgainButton);

        running = false;
        gameController.resetObjs();

        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private void tick() {
        if (gameState.getPhase().equals("start")) {
            gameController.tick(gameCanvas, gameState);
            gameController.updatePlr(plr, gameState);
            gameState.setScore(gameState.getScore()+1);
            scoreLabel.setText("Score: " + String.valueOf(gameState.getScore()) + " Highscore: " + ((GameViewModel) getViewModel()).getLoggedInUser().getHighscore());
        }
    }

    private void render() {
        BufferStrategy bs = gameCanvas.getBufferStrategy();

        if (bs == null) {
            gameCanvas.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        //////////////////////////////////

        g.drawImage(gameState.getBgImage(), 0, 0, gameCanvas.getWidth(), gameCanvas.getHeight(), gameCanvas);

        gameController.render(g);

        //////////////////////////////////
        g.dispose();
        bs.show();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        gameController.keyPressed(key, plr);
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        gameController.keyReleased(key, plr);
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(updates + " Ticks, Fps " + frames);
                updates = 0;
                frames = 0;
            }
        }
        if (!gameState.getPhase().equals("restart")) gameController.exitGame(gameState.getScore(), ((GameViewModel) getViewModel()).getLoggedInUser());
    }

    public void generateTiles(HashMap<String, BufferedImage> tiles) {
        int WIDTH = gameCanvas.getPreferredSize().width;
        int TILEWIDTH = tiles.get("Grass").getWidth();
        float ratio = (float) WIDTH / TILEWIDTH;
        int numTiles = Math.round(ratio) + 2;

        int HEIGHT = gameCanvas.getPreferredSize().height;
        int TILEHEIGHT = tiles.get("Grass").getHeight();
        double y = HEIGHT - TILEHEIGHT;

        for (int i = 0; i < numTiles; i++) {
            double x = (TILEWIDTH * i);
            gameController.addObj(new TileObject(x, y, tiles, ""));
        }
    }

    public Canvas getGameCanvas() { return this.gameCanvas; }
}
