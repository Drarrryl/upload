package view.Options;

import interface_adapter.Options.OptionsController;
import interface_adapter.Options.OptionsViewModel;
import view.View;
import view.ViewManager;
import view.ViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class OptionsView extends View implements PropertyChangeListener {
    private OptionsController optionsController;

    private final JButton backButton;
    private final JButton resetButton;

    private final JComboBox<String> resolutionList;
    private final JComboBox<String> themeList;
    public OptionsView(OptionsViewModel optionsViewModel, OptionsController optionsController) {
        super(optionsViewModel);

        this.optionsController = optionsController;

        JPanel titlePanel = new JPanel();
        titlePanel.add(new JLabel(OptionsViewModel.TITLE_STRING));
        titlePanel.setSize(titlePanel.getWidth(), titlePanel.getHeight()+200);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        JPanel settingsPanel = new JPanel();

        JPanel resolutionPanel = new JPanel();
        resolutionPanel.add(new JLabel(OptionsViewModel.SET_RESOLUTION_STRING));
        String originalSize = optionsViewModel.DEFAULT_SIZE.width + "x" + optionsViewModel.DEFAULT_SIZE.height;
        String[] resolutions = {originalSize, "1920x1080", "1600x900", "1440x900", "1280x800", "1024x768", "800x600", "640x480"};
        resolutionList = new JComboBox<String>(resolutions);
        resolutionList.setSelectedItem(originalSize);
        resolutionList.setSize(resolutionList.getWidth(), titlePanel.getHeight());
        resolutionPanel.add(resolutionList);
        settingsPanel.add(resolutionPanel);

        JPanel themesPanel = new JPanel();
        themesPanel.add(new JLabel(OptionsViewModel.SET_THEME_STRING));
        String[] themes = {"Default", "Inverted"};
        themeList = new JComboBox<String>(themes);
        themesPanel.add(themeList);
        settingsPanel.add(themesPanel);

        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));

        JPanel buttonPanel = new JPanel();
        backButton = new JButton(OptionsViewModel.BACK_BUTTON_STRING);
        resetButton = new JButton(OptionsViewModel.RESET_TO_DEFAULT_BUTTON_STRING);
        buttonPanel.add(backButton);
        buttonPanel.add(resetButton);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        resolutionList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(resolutionList)) {
                    int index = ((JComboBox<String>) e.getSource()).getSelectedIndex();
                    String value = ((JComboBox<String>) e.getSource()).getItemAt(index);
                    optionsController.execute(value, "resolution", optionsViewModel);
                }
            }
        });

        themeList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(themeList)) {
                    int index = ((JComboBox<String>) e.getSource()).getSelectedIndex();
                    String value = ((JComboBox<String>) e.getSource()).getItemAt(index);
                    optionsController.execute(value, "theme", optionsViewModel);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(backButton)) {
                    ViewManager viewManager = optionsViewModel.getViewManager();
                    viewManager.switchToLastView();
                    viewManager.setResolution(viewManager.getLastViewModel().DEFAULT_SIZE);
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(resetButton)) {
                    optionsController.execute(resetButton.getText(), "button", optionsViewModel);
                    resolutionList.setSelectedIndex(0);
                    themeList.setSelectedIndex(0);
                }
            }
        });

        this.add(titlePanel);
        this.add(settingsPanel);
        this.add(buttonPanel);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        optionsViewModel.SetTheme(this, optionsViewModel.getViewManager().getTheme());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
