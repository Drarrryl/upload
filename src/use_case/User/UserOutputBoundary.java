package use_case.User;

public interface UserOutputBoundary {
    void prepareSuccessView(UserOutputData user);

    void prepareFailView(String error);
}
