package use_case.Options;

import java.awt.*;

public interface OptionsOutputBoundary {
    void prepareResolutionView(Dimension newDim);
    void prepareThemeView(String theme);
    void prepareResetView();
}
