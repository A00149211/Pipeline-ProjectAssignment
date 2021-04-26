package edu.ait.library.unit.test;

import edu.ait.library.dto.User;
import edu.ait.library.validation.LibraryException;
import edu.ait.library.validation.LibraryValidationException;
import edu.ait.library.validation.FormErrorMessages;
import edu.ait.library.validation.users.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserValidationTest {
    UserValidator userValidator;
    User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        userValidator = new UserValidator();
        user.setId(4);
        user.setName("Patrick Kelly");
        user.setEmail("A00277135@gmail.com");
        user.setPassword("patrick19");
    }

    //No exception expect validation works
    @Test
    void testAllFieldsOK() throws LibraryException {
        userValidator.validateUser(user);
    }

    @Test
    void testForEmptyNameField() throws LibraryException {
        user.setName("");
        Exception exception = assertThrows(LibraryValidationException.class,()->{
            userValidator.validateUser(user);
        });
        assertEquals(FormErrorMessages.EMPTY_FIELDS.getMsg(),exception.getMessage());
    }

    @Test
    void testForEmptyEmailField() throws LibraryException {
        user.setEmail("");
        Exception exception = assertThrows(LibraryValidationException.class,()->{
            userValidator.validateUser(user);
        });
        assertEquals(FormErrorMessages.EMPTY_FIELDS.getMsg(),exception.getMessage());
    }

    @Test
    void testForEmptyPasswordField() throws LibraryException {
        user.setPassword("");
        Exception exception = assertThrows(LibraryValidationException.class,()->{
            userValidator.validateUser(user);
        });
        assertEquals(FormErrorMessages.EMPTY_FIELDS.getMsg(),exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings= {"firstname.lastname@example.com", "1234567890@example.com", "email@example.co.jp", "email@[123.123.123.123]"})
    void testForValidEmail(String email) throws LibraryException {
        user.setEmail(email);
        userValidator.validateUser(user);
    }

    @Test
    void testForInValidEmail() throws LibraryException {
        user.setEmail("email@example..com");
        Exception exception = assertThrows(LibraryValidationException.class,()->{
            userValidator.validateUser(user);
        });
        assertEquals(FormErrorMessages.INVALID_EMAIL.getMsg(),exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings= {"patrick12", "rty67uuu", "12rubikscube", "emailismypassword123"})
    void testForValidNoPages(String password) throws LibraryException {
        user.setPassword(password);
        userValidator.validateUser(user);
    }

    @Test
    void testForInValidPasswordLength() throws LibraryException {
        user.setPassword("pass12");
        Exception exception = assertThrows(LibraryValidationException.class,()->{
            userValidator.validateUser(user);
        });
        assertEquals(FormErrorMessages.INVALID_PASSW0RD_LENGTH.getMsg(),exception.getMessage());
    }

    @Test
    void testForInValidPasswordLenDig() throws LibraryException {
        user.setPassword("password_3e3");
        Exception exception = assertThrows(LibraryValidationException.class,()->{
            userValidator.validateUser(user);
        });
        assertEquals(FormErrorMessages.INVALID_PASSW0RD_LET_DIG.getMsg(),exception.getMessage());
    }

    @Test
    void testForInValidPasswordLessDig() throws LibraryException {
        user.setPassword("password1");
        Exception exception = assertThrows(LibraryValidationException.class,()->{
            userValidator.validateUser(user);
        });
        assertEquals(FormErrorMessages.INVALID_PASSW0RD_LITTLE_DIG.getMsg(),exception.getMessage());
    }

}
