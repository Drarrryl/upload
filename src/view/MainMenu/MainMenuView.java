package view.MainMenu;

import interface_adapter.MainMenu.MainMenuController;
import interface_adapter.MainMenu.MainMenuViewModel;
import view.View;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

public class MainMenuView extends View implements PropertyChangeListener {

    private MainMenuController mainMenuController;

    private final JButton startButton;
    private final JButton optionsButton;
    private final JButton exitButton;

    public MainMenuView(MainMenuViewModel mainMenuViewModel, MainMenuController mainMenuController) {
        super(mainMenuViewModel);
        this.mainMenuController = mainMenuController;

        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel(MainMenuViewModel.TITLE_TEXT_STRING);
        JLabel loggedInLabel = new JLabel(MainMenuViewModel.LOGGED_IN_AS_STRING);
        if (!Objects.equals(mainMenuViewModel.getLoggedInUser(), null)) {
            loggedInLabel = new JLabel(MainMenuViewModel.LOGGED_IN_AS_STRING + mainMenuViewModel.getLoggedInUser().getUsername());
        }
        titlePanel.add(titleLabel);
        titlePanel.add(loggedInLabel);
        titlePanel.setLayout(new GridLayout(2, 1));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.NORTH);
        loggedInLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loggedInLabel.setVerticalAlignment(SwingConstants.NORTH);

        JPanel buttonPanel = new JPanel();
        startButton = new JButton(MainMenuViewModel.START_BUTTON_STRING);
        optionsButton = new JButton(MainMenuViewModel.OPTIONS_BUTTON_STRING);
        exitButton = new JButton(MainMenuViewModel.EXIT_BUTTON_STRING);
        buttonPanel.add(startButton);
        buttonPanel.add(optionsButton);
        buttonPanel.add(exitButton);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        startButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource().equals(startButton)) {
                        String username = mainMenuViewModel.getLoggedInUser().getUsername();
                        mainMenuController.execute(startButton.getText(), username);
                    }
                }
            }
        );

        optionsButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(optionsButton)) {
                            ViewManager viewManager = mainMenuViewModel.getViewManager();
                            viewManager.switchToView("Options");
                            viewManager.setResolution(viewManager.getLastViewModel().DEFAULT_SIZE);
                        }
                    }
                }
        );

        exitButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(exitButton)) {
                            String username = mainMenuViewModel.getLoggedInUser().getUsername();
                            mainMenuController.execute(exitButton.getText(), username);
                        }
                    }
                }
        );

        JLabel finalLoggedInLabel = loggedInLabel;
        mainMenuViewModel.getState().addPropertyChangeListener(
                new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        if (evt.getPropertyName().equals("username")) {
                            finalLoggedInLabel.setText(finalLoggedInLabel.getText() + evt.getNewValue());
                        }
                    }
        });

        this.setSize(mainMenuViewModel.DEFAULT_SIZE);
        this.add(titlePanel);
        this.add(buttonPanel);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        mainMenuViewModel.SetTheme(this, mainMenuViewModel.getViewManager().getTheme());

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
