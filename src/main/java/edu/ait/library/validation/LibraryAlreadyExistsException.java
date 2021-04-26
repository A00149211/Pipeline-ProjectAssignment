package edu.ait.library.validation;

import edu.ait.library.validation.LibraryException;

public class LibraryAlreadyExistsException extends LibraryException {

    private static final long serialVersionUID = 334051992916748022L;

    public LibraryAlreadyExistsException(final String errorMessage) {
        super(errorMessage);
    }
}
