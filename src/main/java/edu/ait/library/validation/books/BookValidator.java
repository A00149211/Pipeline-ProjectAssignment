package edu.ait.library.validation.books;

import edu.ait.library.dto.Book;
import edu.ait.library.validation.FormErrorMessages;
import edu.ait.library.validation.LibraryValidationException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class BookValidator {
    Book book;
    List<String> listOfValidGenres = Arrays.asList("modernist", "novel", "tragedy", "magic realism", "adventure fiction", "realistic fiction", "romantic");

    public void validateBook(Book book) throws LibraryValidationException {
        this.book = book;
        checkEmptyFields(book);
        checkValidGenre(book, listOfValidGenres);
        checkIfBookHasEnoughPages(book);
        checkPublicationDate(book);
    }

    private void checkEmptyFields(Book book) throws LibraryValidationException {
        if ((book.getTitle().length() == 0) || (book.getAuthor().length() == 0) || (book.getPublisher().length() == 0)
                || (book.getPublishedDate() == null) || (book.getGenre().length() == 0) || (book.getDescription().length() == 0)
                || (book.getNoOfPages() == 0)) {
            throw new LibraryValidationException(FormErrorMessages.EMPTY_FIELDS.getMsg());
        }
    }

    private void checkValidGenre(Book book, List<String> genres) throws LibraryValidationException {
        boolean genreOk = false;
        String genreEntered = book.getGenre().toLowerCase();
        for (String genre : genres) {
            if (genre.equals(genreEntered)) {
                genreOk = true;
                break;
            }
        }
        if (!genreOk) {
            throw new LibraryValidationException(FormErrorMessages.INVALID_GENRE.getMsg());
        }
    }

    private void checkIfBookHasEnoughPages(Book book) throws LibraryValidationException {
        if (book.getNoOfPages() < 10) {
            throw new LibraryValidationException(FormErrorMessages.TOO_FEW_PAGES.getMsg());
        }
    }

    private void checkPublicationDate(Book book) throws LibraryValidationException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
        String presentDate = dtf.format(localDate);
        String publicationDate = dtf.format(book.getPublishedDate());

        if (publicationDate.compareTo(presentDate) > 0 || publicationDate.compareTo(presentDate) == 0) {
            throw new LibraryValidationException(FormErrorMessages.DATE_AFTER_PRESENT.getMsg());
        }

    }

}
