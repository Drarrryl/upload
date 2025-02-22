package view.Login;

import interface_adapter.Login.LoginController;
import interface_adapter.Login.LoginViewModel;
import view.View;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginView extends View implements PropertyChangeListener {
    private LoginViewModel loginViewModel;
    private LoginController loginController;

    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);

    private final JButton loginButton;
    private final JButton signUpButton;

    public LoginView(LoginViewModel loginViewModel, LoginController loginController)
    {
        super(loginViewModel);
        this.loginViewModel = loginViewModel;
        this.loginController = loginController;

        loginButton = new JButton(LoginViewModel.LOGIN_BUTTON_STRING);
        signUpButton = new JButton(LoginViewModel.SIGNUP_BUTTON_STRING);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(LoginViewModel.BACKGROUND_COLOR);
        buttonPanel.add(loginButton);
        buttonPanel.add(signUpButton);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        JPanel usernamePanel = new JPanel();
        usernamePanel.setBackground(LoginViewModel.BACKGROUND_COLOR);
        usernamePanel.add(new JLabel(LoginViewModel.USERNAME_FIELD_STRING));
        usernamePanel.add(usernameInputField);

        JPanel passwordPanel = new JPanel();
        passwordPanel.setBackground(LoginViewModel.BACKGROUND_COLOR);
        passwordPanel.add(new JLabel(LoginViewModel.PASSWORD_FIELD_STRING));
        passwordPanel.add(passwordInputField);

        loginButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(e.getSource().equals(loginButton))
                        {
                            execute();
                        }
                    }
                }
        );

        signUpButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(e.getSource().equals(signUpButton))
                        {
                            loginViewModel.getViewManager().switchToView(loginViewModel.getSignupViewModel().getName());
                            loginViewModel.getViewManager().setResolution(loginViewModel.getSignupViewModel().DEFAULT_SIZE);
                        }
                    }
                }
        );

        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        String text = usernameInputField.getText() + e.getKeyChar();
                        loginViewModel.getState().setUsername(text.replaceAll("\b", ""));
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        String text = passwordInputField.getText() + e.getKeyChar();
                        loginViewModel.getState().setPassword(text.replaceAll("\b", ""));
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            execute();
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        this.add(usernamePanel);
        this.add(passwordPanel);
        this.add(buttonPanel);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        loginViewModel.SetTheme(this, "Default");
    }

    public void execute() {
        String username = loginViewModel.getState().getUsername();
        String password = loginViewModel.getState().getPassword();
        loginController.execute(username, password);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
