package edu.ait.library.validation;

import edu.ait.library.validation.LibraryException;

public class LibraryValidationException extends LibraryException {
    private static final long serialVersionUID = 334051992916748022L;

    public LibraryValidationException(final String errorMessage) {
        super(errorMessage);
    }
}
