package edu.ait.library.unit.test;

import edu.ait.library.dto.Book;
import edu.ait.library.validation.LibraryException;
import edu.ait.library.validation.LibraryValidationException;
import edu.ait.library.validation.books.BookValidator;
import edu.ait.library.validation.FormErrorMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookValidationTest {
    BookValidator bookValidator;
    Book book;

    LocalDate date = LocalDate.of(2020, 1, 8);

    LocalDate testDate1 = LocalDate.of(1867, 11, 12);
    LocalDate testDate2 = LocalDate.of(1990, 2, 3);
    LocalDate testDate3 = LocalDate.of(2005, 4, 21);
    LocalDate testDate4 = LocalDate.of(2025, 4, 21);

    @BeforeEach
    public void setUp() {
        book = new Book();
        bookValidator = new BookValidator();
        book.setId(4);
        book.setTitle("Test");
        book.setAuthor("Test Author - Patrick Kelly");
        book.setPublisher("AIT");
        book.setPublishedDate(date);
        book.setGenre("Novel");
        book.setDescription("This is to test author");
        book.setNoOfPages(222);
        book.setPicture("test.jpg");
    }

    //No exception expect validation works
    @Test
    void testAllFieldsOK() throws LibraryException {
        bookValidator.validateBook(book);
    }

    @Test
    void testForEmptyTitleField() throws LibraryException {
        book.setTitle("");
        Exception exception = assertThrows(LibraryValidationException.class,()->{
            bookValidator.validateBook(book);
        });
        assertEquals(FormErrorMessages.EMPTY_FIELDS.getMsg(),exception.getMessage());
    }

    @Test
    void testForEmptyAuthorField() throws LibraryException {
        book.setAuthor("");
        Exception exception = assertThrows(LibraryValidationException.class,()->{
            bookValidator.validateBook(book);
        });
        assertEquals(FormErrorMessages.EMPTY_FIELDS.getMsg(),exception.getMessage());
    }

    @Test
    void testForEmptyPublisherField() throws LibraryException {
        book.setPublisher("");
        Exception exception = assertThrows(LibraryValidationException.class,()->{
            bookValidator.validateBook(book);
        });
        assertEquals(FormErrorMessages.EMPTY_FIELDS.getMsg(),exception.getMessage());
    }

    @Test
    void testForEmptyPublishedDateField() throws LibraryException {
        book.setPublishedDate(null);
        Exception exception = assertThrows(LibraryValidationException.class,()->{
            bookValidator.validateBook(book);
        });
        assertEquals(FormErrorMessages.EMPTY_FIELDS.getMsg(),exception.getMessage());
    }

    @Test
    void testForEmptyGenreField() throws LibraryException {
        book.setGenre("");
        Exception exception = assertThrows(LibraryValidationException.class,()->{
            bookValidator.validateBook(book);
        });
        assertEquals(FormErrorMessages.EMPTY_FIELDS.getMsg(),exception.getMessage());
    }

    @Test
    void testForEmptyDescriptionField() throws LibraryException {
        book.setDescription("");
        Exception exception = assertThrows(LibraryValidationException.class,()->{
            bookValidator.validateBook(book);
        });
        assertEquals(FormErrorMessages.EMPTY_FIELDS.getMsg(),exception.getMessage());
    }

    @Test
    void testForEmptyNoPagesField() throws LibraryException {
        book.setNoOfPages(0);
        Exception exception = assertThrows(LibraryValidationException.class,()->{
            bookValidator.validateBook(book);
        });
        assertEquals(FormErrorMessages.EMPTY_FIELDS.getMsg(),exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings= {"moDernist", "Novel", "tragedy", "magiC Realism", "adventure FICTION", "realistic fiction", "romANtic"})
    void testForValidGenre(String genre) throws LibraryException {
        book.setGenre(genre);
        bookValidator.validateBook(book);
    }

    @Test
    void testForInValidGenre() throws LibraryException {
        book.setGenre("Fantasy");
        Exception exception = assertThrows(LibraryValidationException.class,()->{
            bookValidator.validateBook(book);
        });
        assertEquals(FormErrorMessages.INVALID_GENRE.getMsg(),exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints= {2000, 140, 83})
    void testForValidNoPages(int noOfPages) throws LibraryException {
        book.setNoOfPages(noOfPages);
        bookValidator.validateBook(book);
    }

    @Test
    void testForInValidNoPages() throws LibraryException {
        book.setNoOfPages(5);
        Exception exception = assertThrows(LibraryValidationException.class,()->{
            bookValidator.validateBook(book);
        });
        assertEquals(FormErrorMessages.TOO_FEW_PAGES.getMsg(),exception.getMessage());
    }

    @Test
    void testForValidPublicationDate() throws LibraryException {
        book.setPublishedDate(testDate1);
        bookValidator.validateBook(book);
        book.setPublishedDate(testDate2);
        bookValidator.validateBook(book);
        book.setPublishedDate(testDate3);
        bookValidator.validateBook(book);
    }

    @Test
    void testForInValidPublicationDate() throws LibraryException {
        book.setPublishedDate(testDate4);
        Exception exception = assertThrows(LibraryValidationException.class,()->{
            bookValidator.validateBook(book);
        });
        assertEquals(FormErrorMessages.DATE_AFTER_PRESENT.getMsg(),exception.getMessage());
    }

}
