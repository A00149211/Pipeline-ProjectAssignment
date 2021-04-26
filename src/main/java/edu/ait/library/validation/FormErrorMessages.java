package edu.ait.library.validation;

public enum FormErrorMessages {
    EMPTY_FIELDS("One or more empty fields"),
    BOOK_ALREADY_EXISTS("Book with given title and author already exists"),
    USER_ALREADY_EXISTS("User with given email and password already exists"),
    BAD_LOGIN("User with given email and password not in system"),
    INVALID_GENRE("Invalid Genre"),
    INVALID_EMAIL("Invalid Email, example: john.smith12@gmail.com"),
    INVALID_PASSW0RD_LENGTH("Invalid Password, password must be 6 characters long "),
    INVALID_PASSW0RD_LET_DIG("Invalid Password, password must only contain letters and digits"),
    INVALID_PASSW0RD_LITTLE_DIG("Invalid Password, password must contain at least 2 digits"),
    TOO_FEW_PAGES("Book doesnt have enough pages, must be at least 10"),
    DATE_AFTER_PRESENT("Book's publication date cant be on or after present date");

    private String errorMessage;

    FormErrorMessages(String errMsg){
        this.errorMessage = errMsg;
    }

    public String getMsg(){
        return errorMessage;
    }
}
