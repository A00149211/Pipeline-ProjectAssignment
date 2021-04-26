package edu.ait.library.dao;

import edu.ait.library.dto.User;
import edu.ait.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDAO {
    @Autowired
    UserRepository userRepository;

    public List<User> listAllUsers() {
        List<User> foundUsers = null;
        try {
            foundUsers = userRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return foundUsers;
    }

    public User getUserById(Integer userId) {
        return userRepository.findById(userId).get();
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }
}
