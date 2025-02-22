package data_access;

import entity.User;

/**
 *
 * create(): saves the object in the database
 * read(): find the object in the database and returns the object
 * update(): find the object and update the object in the database
 * delete(): find the object and delete it in the database
 *
 */
public interface DataAccessInterface  {

    /**
     * Precondition: username does not already exist in the Database (is unique)
     * @exception RuntimeException if there is already a user or a datbase error.
     */
    void createUser(User user) throws RuntimeException;

    /**
     * Returns: null if there are no User with the String provided
     */
    User readUser(String username);

    /**
     * Precondition: username already exist in the Database
     * @exception RuntimeException if there isn't a user or a datbase error.
     */
    void updateUser(User user) throws RuntimeException;

    /**
     * Precondition: username already exist in the Database
     * @exception RuntimeException if there isn't a user or a datbase error.
     */
    void deleteUser(String username) throws RuntimeException;

}
