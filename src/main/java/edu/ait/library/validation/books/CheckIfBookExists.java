package edu.ait.library.validation.books;

import edu.ait.library.dto.Book;
import edu.ait.library.validation.FormErrorMessages;
import edu.ait.library.validation.LibraryAlreadyExistsException;

import java.util.List;

public class CheckIfBookExists {
    Book book;
    List<Book> books;

    public void checkIfBookExists(Book book, List<Book> books) throws LibraryAlreadyExistsException {
        this.book = book;
        this.books = books;
        for (Book b : books) {
            if(b.getTitle().equals(book.getTitle())) {
                if(b.getAuthor().equals(book.getAuthor())) {
                    throw new LibraryAlreadyExistsException(FormErrorMessages.BOOK_ALREADY_EXISTS.getMsg());
                }
            }
        }
    }
}
