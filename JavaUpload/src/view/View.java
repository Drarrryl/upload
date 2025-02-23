package view;

import javax.swing.*;

public class View extends JPanel {
    private ViewModel viewModel;
    public View(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public ViewModel getViewModel() {
        return viewModel;
    }
}
