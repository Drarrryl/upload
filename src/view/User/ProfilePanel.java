package view.User;

import entity.User;
import interface_adapter.user.UserState;
import interface_adapter.user.UserViewModel;
import view.Game.BufferedImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ProfilePanel extends JPanel implements Runnable {
    private User currUser;

    public JButton mainButton;
    private JLabel username;
    private JLabel stats;
    private Canvas imageCanvas;
    private BufferedImage pfp;
    private boolean running;
    private Thread thread;

    private UserState state;
    private UserViewModel viewModel;
    public ProfilePanel(User user, UserViewModel viewModel, boolean button) {
        this.currUser = user;
        this.viewModel = viewModel;

        state = viewModel.getState();

        mainButton = new JButton();

        JPanel namePanel = new JPanel();
        username = new JLabel("[Insert Username]");
        namePanel.add(username);

        JPanel statsPanel = new JPanel();
        stats = new JLabel("[Insert Highscore]");
        statsPanel.add(stats);

        JPanel ImagePanel = new JPanel();
        JPanel NonImagePanel = new JPanel();

        BufferedImageLoader loader = new BufferedImageLoader();

        try {
            pfp = loader.loadImage("/images/pfp.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        imageCanvas = new Canvas();
        imageCanvas.setPreferredSize(new Dimension(50, 50));
        imageCanvas.setMinimumSize(new Dimension(50, 50));
        imageCanvas.setMaximumSize(new Dimension(50, 50));

        ImagePanel.add(imageCanvas);

        NonImagePanel.add(namePanel);
        NonImagePanel.add(statsPanel);
        NonImagePanel.setLayout(new BoxLayout(NonImagePanel, BoxLayout.Y_AXIS));

        state.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("status")) {
                    if (evt.getNewValue().equals(true)) {
                        updateInfo();
                        start();
                    }
                }
            }
        });

        state.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("status")) {
                    if (evt.getNewValue().equals(false)) {
                        stop();
                    }
                }
            }
        });
        if (button) {
            mainButton.add(ImagePanel);
            mainButton.add(NonImagePanel);
            mainButton.setLayout(new BoxLayout(mainButton, BoxLayout.X_AXIS));

            this.add(mainButton);
        } else {
            this.add(ImagePanel);
            this.add(NonImagePanel);
            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        }

        viewModel.SetTheme(this, viewModel.getViewManager().getTheme());
        viewModel.SetTheme(ImagePanel, viewModel.getViewManager().getTheme());
        viewModel.SetTheme(NonImagePanel, viewModel.getViewManager().getTheme());
    }

    private void updateInfo() {
        username.setText(state.getUsername());
        stats.setText("Highscore: " + state.getHighscore());
    }

    public synchronized void start() {
        if (running) {
            return;
        }

        running = true;

        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) {
            return;
        }

        running = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void tick() {

    }

    public void render() {
        BufferStrategy bs = imageCanvas.getBufferStrategy();

        if (bs == null) {
            imageCanvas.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        //////////////////////////////////

        g.drawImage(pfp, 0, 0, 50, 50, null);

        //////////////////////////////////
        g.dispose();
        bs.show();
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
        while (running) {
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
                updates = 0;
                frames = 0;
            }
        }
    }


}