package edu.ait.library.dao;

import edu.ait.library.dto.Book;
import edu.ait.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookDAO {
    @Autowired
    BookRepository bookRepository;

    public List<Book> listAllBooks() {
        List<Book> foundBooks = null;
        try {
            foundBooks = bookRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return foundBooks;
    }

    public Book getBookById(Integer bookId) {
        return bookRepository.findById(bookId).get();
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Integer bookId) {
        bookRepository.deleteById(bookId);
    }
}
