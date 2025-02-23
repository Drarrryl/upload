package view;

import interface_adapter.State;

import javax.swing.*;
import java.awt.*;

public abstract class ViewModel {

    public final String MODEL_NAME;
    protected static final Dimension WINDOW_DEFAULT_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public Dimension DEFAULT_SIZE = new Dimension(WINDOW_DEFAULT_SIZE.width/2, WINDOW_DEFAULT_SIZE.height/2);
    protected Color bg_color;
    protected Color txt_color;
    private ViewManager viewManager;

    public ViewModel(String name, ViewManager viewManager)
    {
        this.MODEL_NAME = name;
        this.viewManager = viewManager;
    }

    public String getName()
    {
        return MODEL_NAME;
    }

    public ViewManager getViewManager()
    {
        return viewManager;
    }

    public void setViewManager(ViewManager viewManager)
    {
        this.viewManager = viewManager;
    }

    public void SetTheme(JPanel panel, String theme) {
        if (theme.equals("Default")) {
            SetComponents(panel, "Background", Color.BLACK);
            SetComponents(panel, "Foreground", Color.WHITE);
        } else if (theme.equals("ViewModel")) {
            if (bg_color == null || txt_color == null) { return; }
            SetComponents(panel, "Background", bg_color);
            SetComponents(panel, "Foreground", txt_color);
        } else if (theme.equals("Inverted")) {
            SetComponents(panel, "Background", Color.WHITE);
            SetComponents(panel, "Foreground", Color.BLACK);
        }
    }

    public void SetComponents(JComponent component, String type, Color color) {
        if (type.equals("Background")) {
            component.setBackground(color);
        } else if (type.equals("Foreground")) {
            component.setForeground(color);
        }

        for (Component curr_component : component.getComponents()) {
            if (curr_component instanceof JSeparator) {
                //continue;
            }
            if (type.equals("Background")) {
                curr_component.setBackground(color);
            } else if (type.equals("Foreground")) {
                curr_component.setForeground(color);
                if (curr_component instanceof JTextField) {
                    ((JTextField) curr_component).setCaretColor(color);
                } else if (curr_component instanceof JPasswordField) {
                    ((JPasswordField) curr_component).setCaretColor(color);
                }
            }
            if (curr_component instanceof JComponent) {
                SetComponents((JComponent) curr_component, type, color);
            }
        }
    }

}