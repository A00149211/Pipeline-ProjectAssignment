package edu.ait.library.validation.users;

import edu.ait.library.dto.User;
import edu.ait.library.validation.FormErrorMessages;
import edu.ait.library.validation.LibraryValidationException;
import org.apache.commons.validator.routines.EmailValidator;

public class UserValidator {
    User user;

    public void validateUser(User user) throws LibraryValidationException {
        this.user = user;
        checkEmptyFields(user);
        checkIfEmailIsValid(user);
        checkIfPasswordIsValid(user);
    }

    private void checkEmptyFields(User user) throws LibraryValidationException {
        if ((user.getName().length() == 0) || (user.getEmail().length() == 0) || (user.getPassword().length() == 0)) {
            throw new LibraryValidationException(FormErrorMessages.EMPTY_FIELDS.getMsg());
        }
    }

    private void checkIfEmailIsValid(User user) throws LibraryValidationException {
        String enteredEmail = user.getEmail();
        EmailValidator emailValidator = EmailValidator.getInstance();
        if(!emailValidator.isValid(enteredEmail)) {
            throw new LibraryValidationException(FormErrorMessages.INVALID_EMAIL.getMsg());
        }
    }

    private void checkIfPasswordIsValid(User user) throws LibraryValidationException {
        String enteredPassword = user.getPassword();
        if (enteredPassword.length() < 8) {
            throw new LibraryValidationException(FormErrorMessages.INVALID_PASSW0RD_LENGTH.getMsg());
        } else {
            char character;
            int count = 0;
            for (int i = 0; i < enteredPassword.length(); i++) {
                character = enteredPassword.charAt(i);
                if (!Character.isLetterOrDigit(character)) {
                    throw new LibraryValidationException(FormErrorMessages.INVALID_PASSW0RD_LET_DIG.getMsg());
                } else if (Character.isDigit(character)) {
                    count++;
                }
            }
            if (count < 2)   {
                throw new LibraryValidationException(FormErrorMessages.INVALID_PASSW0RD_LITTLE_DIG.getMsg());
            }
        }
    }

}
