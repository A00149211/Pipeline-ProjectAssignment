package edu.ait.library.repositories;

import edu.ait.library.dto.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findByPublisher(String publisher);
}
