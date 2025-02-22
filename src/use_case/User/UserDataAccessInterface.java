package use_case.User;

import entity.User;

public interface UserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User user);
}
