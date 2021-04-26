package edu.ait.library.controllers;

import edu.ait.library.dao.BookDAO;
import edu.ait.library.dto.Book;
import edu.ait.library.validation.LibraryAlreadyExistsException;
import edu.ait.library.validation.LibraryValidationException;
import edu.ait.library.validation.books.BookValidator;
import edu.ait.library.validation.books.CheckIfBookExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookDAO bookDAO;

    BookValidator bookValidator = new BookValidator();
    CheckIfBookExists checkIfBookExists = new CheckIfBookExists();

    @RequestMapping("landing")
    public String landingPage() {
        return "landing_page";
    }

    @GetMapping("summary")
    public String showSummaryOfBooks(Model model) {
        List<Book> listOfBooks = bookDAO.listAllBooks();
        model.addAttribute("listOfBooks", listOfBooks);
        return "summary";
    }

    @GetMapping("books")
    public ModelAndView showListOfBooks() {
        List<Book> listOfBooks = bookDAO.listAllBooks();
        ModelAndView model = new ModelAndView();
        model.addObject("listOfBooks", listOfBooks);
        model.setViewName("books");

        return model;
    }

    @GetMapping("new")
    public ModelAndView showAddBookForm(Book book) {
        ModelAndView model = new ModelAndView();
        model.addObject("book", book);
        model.setViewName("add_book");
        return model;
    }

    @PostMapping("save")
    public String addBook(Book book) {
        try {
            List<Book> listOfBooks = bookDAO.listAllBooks();
            bookValidator.validateBook(book);
            checkIfBookExists.checkIfBookExists(book, listOfBooks);
        }catch (LibraryValidationException | LibraryAlreadyExistsException e) {
            System.err.println(e.getMessage());
            return"add_book";
        }
        bookDAO.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("edit/{id}")
    public ModelAndView showEditBookForm(@PathVariable("id") int bookId) {
        Book book = bookDAO.getBookById(bookId);
        ModelAndView model = new ModelAndView();
        model.addObject("book", book);
        model.setViewName("edit_book");
        return model;
    }

    @PostMapping("update/{id}")
    public ModelAndView updateBook(@PathVariable("id") int bookId, Book book) {
        bookDAO.addBook(book);
        List<Book> listOfBooks = bookDAO.listAllBooks();
        ModelAndView model = new ModelAndView();
        model.addObject("listOfBooks", listOfBooks);
        model.setViewName("redirect:/books");
        return model;
    }

    @GetMapping("delete/{id}")
    public ModelAndView deleteBook(@PathVariable("id") int bookId) {
        bookDAO.deleteBook(bookId);
        List<Book> listOfBooks = bookDAO.listAllBooks();
        ModelAndView model = new ModelAndView();
        model.addObject("listOfBooks", listOfBooks);
        model.setViewName("redirect:/books");
        return model;
    }
}
