package edu.ait.library.mocks;

import edu.ait.library.dao.UserDAO;
import edu.ait.library.dto.User;
import edu.ait.library.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserMockTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserDAO userDAO;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        List<User> listOfUsers = new ArrayList<User>();
        listOfUsers.add(new User("Patrick Kelly", "patrick@gmail.com", "patrick19"));
        listOfUsers.add(new User("David O'Rourke", "david@hotmail.com", "david21a"));
        listOfUsers.add(new User("Steve Lawson", "steve@outlook.com", "steve45a"));
        when(userRepository.findAll()).thenReturn(listOfUsers);
        List<User> result = userDAO.listAllUsers();
        assertEquals(3, result.size());

        User user = result.get(0);
        assertEquals("Patrick Kelly", user.getName());
        user = result.get(1);
        assertEquals("David O'Rourke", user.getName());
        user = result.get(2);
        assertEquals("Steve Lawson", user.getName());
        assertEquals("steve@outlook.com", user.getEmail());
        assertEquals("steve45a", user.getPassword());
    }

    @Test
    public void testGetUserById() {
        Optional<User> user = Optional.of(new User("Patrick Kelly", "patrick@gmail.com", "patrick19"));
        when(userRepository.findById(1)).thenReturn(user);
        User result = userDAO.getUserById(1);
        assertEquals("Patrick Kelly", result.getName());
        assertEquals("patrick@gmail.com", result.getEmail());
        assertEquals("patrick19", result.getPassword());
    }

    @Test
    public void testAddBook(){
        User user = new User("Patrick Kelly", "patrick@gmail.com", "patrick19");
        when(userRepository.save(user)).thenReturn(user);
        User result = userDAO.addUser(user);

        verify(userRepository, times(1)).save(user);
        assertEquals("Patrick Kelly", result.getName());
        assertEquals("patrick@gmail.com", result.getEmail());
        assertEquals("patrick19", result.getPassword());
    }

    @Test
    public void testDeleteBook(){
        User user1 = createUser(1, "Patrick Kelly", "patrick@gmail.com", "patrick19");
        userDAO.deleteUser(user1.getId());
        verify(userRepository, times(0)).delete(user1);
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