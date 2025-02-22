package interface_adapter.Options;

import interface_adapter.user.UserViewModel;
import use_case.MainMenu.MainMenuOutputBoundary;
import use_case.MainMenu.MainMenuOutputData;
import use_case.Options.OptionsOutputBoundary;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class OptionsPresenter implements OptionsOutputBoundary {
    private OptionsViewModel optionsViewModel;
    private ViewManager viewManager;

    public OptionsPresenter(ViewManager viewManager, OptionsViewModel optionsViewModel)
    {
        this.optionsViewModel = optionsViewModel;
        this.viewManager = viewManager;
    }

    @Override
    public void prepareResolutionView(Dimension newDim) {
        viewManager.setAllResolution(newDim);
        viewManager.setResolution(newDim);
    }

    @Override
    public void prepareThemeView(String theme) {
        ViewManager viewManager = optionsViewModel.getViewManager();
        viewManager.setTheme(theme);
        viewManager.updateThemes();
    }

    @Override
    public void prepareResetView() {
        Dimension res = optionsViewModel.getState().getDefaultRes();
        String theme = optionsViewModel.getState().getDefaultTheme();

        prepareResolutionView(res);
        prepareThemeView(theme);
        JOptionPane.showMessageDialog(null, "Default settings applied!");
    }
}
