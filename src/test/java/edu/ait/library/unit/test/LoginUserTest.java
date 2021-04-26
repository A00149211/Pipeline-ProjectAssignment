package edu.ait.library.unit.test;

import edu.ait.library.dto.User;
import edu.ait.library.validation.LibraryAlreadyExistsException;
import edu.ait.library.validation.LibraryException;
import edu.ait.library.validation.users.LoginCheckUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LoginUserTest {

    User user1 = createUser(1, "Patrick Kelly", "patrick@gmail.com", "patrick19");
    User user2 = createUser(2, "David O'Rourke", "david@hotmail.com", "david21a");
    User user3 = createUser(3, "Steve Lawson", "steve@outlook.com", "steve45a");
    List<User> users = new ArrayList<User>();
    LoginCheckUsers loginCheckUsers;

    @BeforeEach
    void setUp() {
        users.add(user1);
        users.add(user2);
        users.add(user3);
        loginCheckUsers = new LoginCheckUsers();
    }

    @Test
    void testValidUserLoginOK() throws LibraryException {
        User user = new User();
        user.setId(3);
        user.setName("Steve Lawson");
        user.setEmail("steve@outlook.com");
        user.setPassword("steve45a");
        loginCheckUsers.loginCheckUsers(user, users);
    }

    @Test
    void testUserLoginException() throws LibraryException {
        User user = new User();
        user.setId(4);
        user.setName("Rachael Stephens");
        user.setEmail("r.stephens@gmail.com");
        user.setPassword("athlone40ab");
        Exception exception = assertThrows(LibraryAlreadyExistsException.class,()->{
            loginCheckUsers.loginCheckUsers(user, users);
        });
    }

    private User createUser(int id, String name, String email, String password) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }
}
