package edu.ait.library.controllers;

import edu.ait.library.dao.UserDAO;
import edu.ait.library.dto.User;
import edu.ait.library.validation.LibraryAlreadyExistsException;
import edu.ait.library.validation.LibraryValidationException;
import edu.ait.library.validation.users.CheckIfUserExists;
import edu.ait.library.validation.users.LoginCheckUsers;
import edu.ait.library.validation.users.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserDAO userDAO;

    UserValidator userValidator = new UserValidator();
    CheckIfUserExists checkIfUserExists = new CheckIfUserExists();
    LoginCheckUsers checkUsers = new LoginCheckUsers();

    @GetMapping("users")
    public ModelAndView showListOfUsers() {
        List<User> listOfUsers = userDAO.listAllUsers();
        ModelAndView model = new ModelAndView();
        model.addObject("listOfUsers", listOfUsers);
        model.setViewName("users");

        return model;
    }

    @GetMapping("login")
    public ModelAndView showUserLoginForm(User user) {
        ModelAndView model = new ModelAndView();
        model.addObject("user", user);
        model.setViewName("login");
        return model;
    }

    @PostMapping("loginuser")
    public String loginUser(User user) {
        try {
            List<User> listOfUsers = userDAO.listAllUsers();
            checkUsers.loginCheckUsers(user, listOfUsers);
        }catch (LibraryAlreadyExistsException e) {
            System.err.println(e.getMessage());
            System.out.println("GET THERE");
            return"login";
        }
        return "redirect:/landing";
    }

    @GetMapping("/")
    public ModelAndView showAddUserForm(User user) {
        ModelAndView model = new ModelAndView();
        model.addObject("user", user);
        model.setViewName("add_user");
        return model;
    }

    @PostMapping("saveuser")
    public String addUser(User user) {
        try {
            List<User> listOfUsers = userDAO.listAllUsers();
            userValidator.validateUser(user);
            checkIfUserExists.checkIfUserExists(user, listOfUsers);
        }catch (LibraryValidationException | LibraryAlreadyExistsException e) {
            System.err.println(e.getMessage());
            return"add_user";
        }
        userDAO.addUser(user);
        return "redirect:/login";
    }

    @GetMapping("edituser/{id}")
    public ModelAndView showEditUserForm(@PathVariable("id") int bookId) {
        User user = userDAO.getUserById(bookId);
        ModelAndView model = new ModelAndView();
        model.addObject("user", user);
        model.setViewName("edit_user");
        return model;
    }

    @PostMapping("updateuser/{id}")
    public ModelAndView updateUser(@PathVariable("id") int userId, User user) {
        userDAO.addUser(user);
        List<User> listOfUsers = userDAO.listAllUsers();
        ModelAndView model = new ModelAndView();
        model.addObject("listOfUsers", listOfUsers);
        model.setViewName("redirect:/users");
        return model;
    }

    @GetMapping("deleteuser/{id}")
    public ModelAndView deleteUser(@PathVariable("id") int userId) {
        userDAO.deleteUser(userId);
        List<User> listOfUsers = userDAO.listAllUsers();
        ModelAndView model = new ModelAndView();
        model.addObject("listOfUsers", listOfUsers);
        model.setViewName("redirect:/users");
        return model;
    }
}
