package edu.ait.library.validation.users;

import edu.ait.library.dto.User;
import edu.ait.library.validation.FormErrorMessages;
import edu.ait.library.validation.LibraryAlreadyExistsException;

import java.util.List;

public class CheckIfUserExists {
    User user;
    List<User> users;

    public void checkIfUserExists(User user, List<User> users) throws LibraryAlreadyExistsException {
        this.user = user;
        this.users = users;
        for (User u : users) {
            if(u.getEmail().equals(user.getEmail())) {
                if(u.getEmail().equals(user.getEmail())) {
                    throw new LibraryAlreadyExistsException(FormErrorMessages.USER_ALREADY_EXISTS.getMsg());
                }
            }
        }
    }
}
