package edu.ait.library.validation.users;

import edu.ait.library.dto.User;
import edu.ait.library.validation.FormErrorMessages;
import edu.ait.library.validation.LibraryAlreadyExistsException;

import java.util.List;

public class LoginCheckUsers {
    User user;
    List<User> users;

    public void loginCheckUsers(User user, List<User> users) throws LibraryAlreadyExistsException {
        this.user = user;
        this.users = users;

        int count = 0;

        for (User u : users) {
            if (u.getEmail().equals(user.getEmail()) && u.getPassword().equals(user.getPassword())) {
                count++;
            }
        }

        if(count!=1) {
            throw new LibraryAlreadyExistsException(FormErrorMessages.BAD_LOGIN.getMsg());
        }
    }
}
