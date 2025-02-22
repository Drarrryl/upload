package view.MainMenu;

import interface_adapter.MainMenu.MainMenuController;
import interface_adapter.MainMenu.MainMenuViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainMenuView extends JPanel implements PropertyChangeListener {

    private MainMenuViewModel mainMenuViewModel;
    private MainMenuController mainMenuController;

    private JButton startButton;
    private JButton exitButton;

    public MainMenuView(MainMenuViewModel mainMenuViewModel, MainMenuController mainMenuController) {
        this.mainMenuViewModel = mainMenuViewModel;
        this.mainMenuController = mainMenuController;

        startButton = new JButton(MainMenuViewModel.START_BUTTON_STRING);
        exitButton = new JButton(MainMenuViewModel.EXIT_BUTTON_STRING);

        startButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource().equals(startButton)) {
                        String username = mainMenuViewModel.getLoggedInUser().getUsername();
                        mainMenuController.execute(startButton.getName(), username);
                    }
                }
            }
        );

        exitButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource().equals(exitButton)) {
                        mainMenuViewModel.getViewManager().closeView();
                    }
                }
            }
        );

        this.add(startButton);
        this.add(exitButton);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
