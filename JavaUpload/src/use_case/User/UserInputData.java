package use_case.User;

import entity.User;

import java.time.LocalDateTime;

public class UserInputData {

    final private User user;

    public UserInputData(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
