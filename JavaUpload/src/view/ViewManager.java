package view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Objects;

public class ViewManager {

    HashMap<String, View> views;
    String lastView, currentView;

    JFrame applicationFrame;

    String theme;

    public ViewManager(JFrame applicationFrame)
    {
        views = new HashMap<>();
        this.applicationFrame = applicationFrame;
        lastView = null;
        currentView = null;
        theme = "Default";
    }

    public void addView(View view, String name)
    {
        views.put(name, view);
    }

    public void switchToView(String name)
    {
        lastView = currentView;
        currentView = name;
        applicationFrame.setJMenuBar(null);

        if(lastView != null)
        {
            applicationFrame.remove(views.get(lastView));
        }
        if(currentView != null)
        {
            applicationFrame.add(views.get(currentView));
            applicationFrame.pack();
        }
    }

    public void closeView() {
        applicationFrame.dispose();
    }

    public String getLastView()
    {
        return lastView;
    }
    public String getCurrentView()
    {
        return currentView;
    }
    public ViewModel getLastViewModel() {
        if (!lastView.isEmpty()) {
            return views.get(lastView).getViewModel();
        }
        return null;
    }
    public ViewModel getViewModel(String viewModel) {
        return views.get(viewModel).getViewModel();
    }

    public JFrame getApplicationFrame()
    {
        return applicationFrame;
    }

    public void switchToLastView()
    {
        switchToView(lastView);
    }

    public void setResolution(Dimension dimension) { applicationFrame.setSize(dimension); }

    public void setAllResolution(Dimension dimension) {
        for (View currView : views.values()) {
            if (Objects.equals(currView.getViewModel().getName(), "Login")) continue;
            if (Objects.equals(currView.getViewModel().getName(), "Signup")) continue;

            currView.getViewModel().DEFAULT_SIZE = dimension;
        }
    }

    public String getTheme() { return theme; }

    public void setTheme(String newTheme) { this.theme = newTheme; }

    public void updateThemes() {
        for (View currView : views.values()) {
            if (Objects.equals(currView.getViewModel().getName(), "Login")) continue;
            if (Objects.equals(currView.getViewModel().getName(), "Signup")) continue;

            currView.getViewModel().SetTheme(currView, theme);
        }
    }


    public void showErrorMessage(String error)
    {
        JOptionPane.showMessageDialog(views.get(currentView), error);
    }
}