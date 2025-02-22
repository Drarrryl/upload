package interface_adapter.Options;

import use_case.MainMenu.MainMenuInputBoundary;
import use_case.MainMenu.MainMenuInputData;
import use_case.Options.OptionsOutputBoundary;
import view.ViewManager;

import java.awt.*;
import java.util.Arrays;

public class OptionsController {
    final OptionsOutputBoundary optionsPresenter;

    public OptionsController(OptionsOutputBoundary optionsPresenter)
    {
        this.optionsPresenter = optionsPresenter;
    }

    public void execute(String value, String type, OptionsViewModel optionsViewModel)
    {
        if (type.equals("resolution")) {
            String[] values = value.split("x");
            Dimension newDim = new Dimension();
            newDim.setSize(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
            optionsPresenter.prepareResolutionView(newDim);
        } else if (type.equals("theme")) {
            optionsPresenter.prepareThemeView(value);
        } else if (type.equals("button")) {
            if (value.equals("Reset to Default")) {
                optionsPresenter.prepareResetView();
            }
        }
    }
}
