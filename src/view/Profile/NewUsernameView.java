package view.Profile;

import interface_adapter.Login.LoginViewModel;
import interface_adapter.Profile.ProfileViewModel;
import view.View;

import javax.swing.*;

public class NewUsernameView extends View {

    private JFrame frame;

    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);

    public final JButton backButton;
    public final JButton submitButton;

    public NewUsernameView(ProfileViewModel viewModel) {
        super(viewModel);

        frame = new JFrame("New Username");
        frame.setPreferredSize(viewModel.NEW_FRAME_DIMENSION);
        frame.setMaximumSize(viewModel.NEW_FRAME_DIMENSION);
        frame.setMinimumSize(viewModel.NEW_FRAME_DIMENSION);
        frame.setVisible(true);
        frame.setResizable(false);

        JPanel usernamePanel = new JPanel();
        usernamePanel.setBackground(LoginViewModel.BACKGROUND_COLOR);
        usernamePanel.add(new JLabel(LoginViewModel.USERNAME_FIELD_STRING));
        usernamePanel.add(usernameInputField);

        JPanel passwordPanel = new JPanel();
        passwordPanel.setBackground(LoginViewModel.BACKGROUND_COLOR);
        passwordPanel.add(new JLabel(LoginViewModel.PASSWORD_FIELD_STRING));
        passwordPanel.add(passwordInputField);

        backButton = new JButton(viewModel.BACK_BUTTON_STRING);
        submitButton = new JButton(viewModel.SUBMIT_BUTTON_STRING);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(LoginViewModel.BACKGROUND_COLOR);
        buttonPanel.add(backButton);
        buttonPanel.add(submitButton);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        this.add(usernamePanel);
        this.add(passwordPanel);
        this.add(buttonPanel);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        frame.add(this);
    }

    public void close() {
        frame.dispose();
    }
}
