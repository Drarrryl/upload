package interface_adapter.user;

import use_case.User.UserInputBoundary;
import use_case.User.UserInputData;

import java.time.LocalDateTime;

public class UserController {

    private final UserInputBoundary userInputBoundary;

    public UserController(UserInputBoundary userInputBoundary)
    {
        this.userInputBoundary = userInputBoundary;
    }

    public void executeAdd
            (
                    String name,
                    String description,
                    LocalDateTime start,
                    LocalDateTime end,
                    String requestedTo,
                    boolean status,
                    String user
            )
    {
        UserInputData input = new UserInputData
                (
                        name,
                        description,
                        start,
                        end,
                        requestedTo,
                        status,
                        user
                );
        userInputBoundary.execute(input);
    }
}
