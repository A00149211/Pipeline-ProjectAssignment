package edu.ait.library.mocks;

import edu.ait.library.dao.BookDAO;
import edu.ait.library.dto.Book;
import edu.ait.library.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BookMockTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookDAO bookDAO;

    LocalDate date = LocalDate.of(2020, 1, 8);

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllBooks() {
        List<Book> listOfBooks = new ArrayList<Book>();
        listOfBooks.add(new Book("The Catcher in the Rye", "J. D. Salinger", "Little, Brown and Company", date, "Realistic Fiction", "The novel details two days in the life of 16-year-old Holden Caulfield after he has been expelled from prep school. Confused and disillusioned, Holden searches for truth and rails against the “phoniness” of the adult world.", 234, "The_Catcher_in_the_Rye.jpg"));
        listOfBooks.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", "Charles Scribners Sons", date, "Tragedy", "Jay Gatsby - The title character and protagonist of the novel, Gatsby is a fabulously wealthy young man living in a Gothic mansion in West Egg. He is famous for the lavish parties he throws every Saturday night, but no one knows where he comes from, what he does, or how he made his fortune.", 218, "The_Great_Gatsby.jpeg"));
        listOfBooks.add(new Book("Lolita", "Vladimir Nabokov", "Olympia Press", date, "Novel", "Lolita, of the Confession of a White Widowed Male by Vladimir Nabokov is a story about Humbert Humbert, a literature professor in his late thirties, obsessed with a twelve-year-old Dolores Haze.", 336, "Lolita.jpg"));
        when(bookRepository.findAll()).thenReturn(listOfBooks);
        List<Book> result = bookDAO.listAllBooks();
        assertEquals(3, result.size());

        Book book = result.get(0);
        assertEquals("The Catcher in the Rye", book.getTitle());
        book = result.get(1);
        assertEquals("The Great Gatsby", book.getTitle());
        book = result.get(2);
        assertEquals("Lolita", book.getTitle());
        assertEquals("Vladimir Nabokov", book.getAuthor());
        assertEquals("Olympia Press", book.getPublisher());
        assertEquals(date, book.getPublishedDate());
        assertEquals("Novel", book.getGenre());
        assertEquals("Lolita, of the Confession of a White Widowed Male by Vladimir Nabokov is a story about Humbert Humbert, a literature professor in his late thirties, obsessed with a twelve-year-old Dolores Haze.", book.getDescription());
        assertEquals(336, book.getNoOfPages());
        assertEquals("Lolita.jpg", book.getPicture());
    }

//    @Test
//    void testFindAllBooksException() throws Exception {
//        List<Book> listOfBooks = new ArrayList<Book>();
//        when(bookRepository.findAll()).thenReturn(listOfBooks).thenThrow(RuntimeException.class);
//        Exception exception = assertThrows(Exception.class,()->{
//            bookDAO.listAllBooks();
//        });
//    }

    @Test
    public void testGetBookById() {
        Optional<Book> book = Optional.of(new Book("The Catcher in the Rye", "J. D. Salinger", "Little, Brown and Company", date, "Realistic Fiction", "The novel details two days in the life of 16-year-old Holden Caulfield after he has been expelled from prep school. Confused and disillusioned, Holden searches for truth and rails against the “phoniness” of the adult world.", 234, "The_Catcher_in_the_Rye.jpg"));
        when(bookRepository.findById(1)).thenReturn(book);
        Book result = bookDAO.getBookById(1);
        assertEquals("The Catcher in the Rye", result.getTitle());
        assertEquals(234, result.getNoOfPages());
    }

    @Test
    public void testAddBook(){
        Book book = new Book("The Catcher in the Rye", "J. D. Salinger", "Little, Brown and Company", date, "Realistic Fiction", "The novel details two days in the life of 16-year-old Holden Caulfield after he has been expelled from prep school. Confused and disillusioned, Holden searches for truth and rails against the “phoniness” of the adult world.", 234, "The_Catcher_in_the_Rye.jpg");
        when(bookRepository.save(book)).thenReturn(book);
        Book result = bookDAO.addBook(book);

        verify(bookRepository, times(1)).save(book);
        assertEquals("The Catcher in the Rye", result.getTitle());
        assertEquals(234, result.getNoOfPages());
    }

    @Test
    public void testDeleteBook(){
        Book book = createBook(1,"The Catcher in the Rye", "J. D. Salinger", "Little, Brown and Company", date, "Realistic Fiction", "The novel details two days in the life of 16-year-old Holden Caulfield after he has been expelled from prep school. Confused and disillusioned, Holden searches for truth and rails against the “phoniness” of the adult world.", 234, "The_Catcher_in_the_Rye.jpg");
        bookDAO.deleteBook(book.getId());
        verify(bookRepository, times(0)).delete(book);
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
