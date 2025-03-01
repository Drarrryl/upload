package view.Signup;

import interface_adapter.Signup.SignupController;
import interface_adapter.Signup.SignupViewModel;
import view.View;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SignupView extends View {
    private SignupViewModel signupViewModel;
    private SignupController signupController;

    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField1 = new JPasswordField(15);
    private final JPasswordField passwordInputField2 = new JPasswordField(15);

    private JButton backButton;
    private JButton signUpButton;

    public SignupView(SignupViewModel signupViewModel, SignupController signupController)
    {
        super(signupViewModel);
        this.signupViewModel = signupViewModel;
        this.signupController = signupController;

        backButton = new JButton(SignupViewModel.BACK_BUTTON_STRING);
        signUpButton = new JButton(SignupViewModel.SIGNUP_BUTTON_STRING);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(signUpButton);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        JPanel usernamePanel = new JPanel();
        usernamePanel.add(new JLabel(SignupViewModel.USERNAME_FIELD_STRING));
        usernamePanel.add(usernameInputField);

        JPanel passwordPanel1 = new JPanel();
        passwordPanel1.add(new JLabel(SignupViewModel.PASSWORD_FIELD_STRING1));
        passwordPanel1.add(passwordInputField1);

        JPanel passwordPanel2 = new JPanel();
        passwordPanel2.add(new JLabel(SignupViewModel.PASSWORD_FIELD_STRING2));
        passwordPanel2.add(passwordInputField2);

        backButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ViewManager viewManager = signupViewModel.getViewManager();
                        viewManager.switchToLastView();
                        viewManager.setResolution(viewManager.getLastViewModel().DEFAULT_SIZE);
                    }
                }
        );

        signUpButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        execute();
                    }
                }
        );

        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        String text = usernameInputField.getText() + e.getKeyChar();
                        signupViewModel.getState().setUsername(text.replaceAll("\b", ""));
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        passwordInputField1.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        String text = passwordInputField1.getText() + e.getKeyChar();
                        signupViewModel.getState().setPassword(text.replaceAll("\b", ""));
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        passwordInputField2.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        String text = passwordInputField2.getText() + e.getKeyChar();
                        signupViewModel.getState().setRepeatPassword(text.replaceAll("\b", ""));
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
        this.add(passwordPanel1);
        this.add(passwordPanel2);
        this.add(buttonPanel);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        signupViewModel.SetTheme(this, "Default");
    }

    public void execute() {
        String username = signupViewModel.getState().getUsername();
        String password = signupViewModel.getState().getPassword();
        String repeatPassword = signupViewModel.getState().getRepeatPassword();
        signupController.execute(username, password, repeatPassword);
    }
}
