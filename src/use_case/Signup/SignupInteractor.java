package use_case.Signup;

import java.util.ArrayList;
import java.util.List;

import Factory.UserFactory;
import data_access.DataAccessInterface;
import entity.User;

import java.time.LocalDateTime;

public class SignupInteractor implements SignupInputBoundary {
    final DataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final UserFactory userFactory;

    public SignupInteractor(DataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    private boolean emptyName(String name) {
        for (int i = 0; i < name.length()-1; i++) {
            if (name.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        String username = signupInputData.getUsername();
        String password = signupInputData.getPassword();
        if (userDataAccessObject.readUser(signupInputData.getUsername()) != null) {
            userPresenter.prepareFailView("User already exists.");
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else if (username.isBlank()) {
            userPresenter.prepareFailView("Invalid Username!");
        } else if (password.isBlank()) {
            userPresenter.prepareFailView("Invalid Password!");
        } else {

            LocalDateTime ltd = LocalDateTime.now();
            User user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword());
            userDataAccessObject.createUser(user);

            SignupOutputData signupOutputData = new SignupOutputData(user.getUsername(), ltd.toString(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}
