package edu.ait.library.unit.test;

import edu.ait.library.dto.Book;
import edu.ait.library.validation.LibraryAlreadyExistsException;
import edu.ait.library.validation.LibraryException;
import edu.ait.library.validation.books.CheckIfBookExists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CheckBookExistsTest {
    LocalDate date = LocalDate.of(2020, 1, 8);

    Book book1 = createBook(1,"The Catcher in the Rye", "J. D. Salinger", "Little, Brown and Company", date, "Realistic Fiction", "The novel details two days in the life of 16-year-old Holden Caulfield after he has been expelled from prep school. Confused and disillusioned, Holden searches for truth and rails against the “phoniness” of the adult world.", 234, "The_Catcher_in_the_Rye.jpg");
    Book book2 = createBook(2,"Lolita", "'Vladimir Nabokov", "Olympia Press", date, "Novel", "Lolita, of the Confession of a White Widowed Male by Vladimir Nabokov is a story about Humbert Humbert, a literature professor in his late thirties, obsessed with a twelve-year-old Dolores Haze.", 336, "Lolita.jpg");
    Book book3 = createBook(3,"In Search of Lost Time", "Marcel Proust", "Grasset", date, "Modernist", "In Search of Lost Time is a fictional autobiography by a man whose life almost mirrors that of Marcel Proust. The first forty pages of the novel describe the narrator as a young boy in bed awaiting, and as a middle-aged man remembering, his mothers good-night kiss.", 4215, "In_Search_Of_Lost_Time.jpg");
    List<Book> books = new ArrayList<Book>();
    CheckIfBookExists checkIfBookExists;

    @BeforeEach
    void setUp() {
        books.add(book1);
        books.add(book2);
        books.add(book3);
        checkIfBookExists = new CheckIfBookExists();
    }

    @Test
    void testBookTitleExistsOK() throws LibraryException {
        Book book = new Book();
        book.setId(4);
        book.setTitle("Test");
        book.setAuthor("Test Author - Patrick Kelly");
        book.setPublisher("AIT");
        book.setPublishedDate(date);
        book.setGenre("Novel");
        book.setDescription("This is to test author");
        book.setNoOfPages(222);
        book.setPicture("test.jpg");
        checkIfBookExists.checkIfBookExists(book, books);
    }

    @Test
    void testBookAuthorExistsOK() throws LibraryException {
        Book book = new Book();
        book.setId(4);
        book.setTitle("Test Book Title - Patrick Kelly");
        book.setAuthor("Patrick Kelly");
        book.setPublisher("AIT");
        book.setPublishedDate(date);
        book.setGenre("Novel");
        book.setDescription("This is to test author");
        book.setNoOfPages(222);
        book.setPicture("test.jpg");
        checkIfBookExists.checkIfBookExists(book, books);
    }

    @Test
    void testBookExistsException() throws LibraryException {
        Book book = new Book();
        book.setId(4);
        book.setTitle("The Catcher in the Rye");
        book.setAuthor("J. D. Salinger");
        book.setPublisher("AIT");
        book.setPublishedDate(date);
        book.setGenre("Novel");
        book.setDescription("This is to test author");
        book.setNoOfPages(222);
        book.setPicture("test.jpg");
        Exception exception = assertThrows(LibraryAlreadyExistsException.class,()->{
            checkIfBookExists.checkIfBookExists(book, books);
        });
    }

    private Book createBook(int id, String title, String author, String publisher, LocalDate publishedDate, String genre, String description, int noOfPages, String picture) {
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setPublishedDate(publishedDate);
        book.setGenre(genre);
        book.setDescription(description);
        book.setNoOfPages(noOfPages);
        book.setPicture(picture);
        return book;
    }
}
