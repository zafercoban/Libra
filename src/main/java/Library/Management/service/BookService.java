package Library.Management.service;

import java.util.List;
import java.util.UUID;

import Library.Management.repository.BookRepository;
import Library.Management.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book create(Book book) {
        book.setId(UUID.randomUUID().toString());
        return bookRepository.save(book);
    }

    public Book read(Book book) {
        return book;
    }

    public List<Book> readAll() {
        return bookRepository.findAll();
    }

    public Book update(Book book) {
        bookRepository.delete(book.getId());
        return bookRepository.save(book);
    }

    public Boolean delete(Book book) {
        bookRepository.delete(book);
        return true;
    }

    public Book findById(String bookId) {
        return bookRepository.findOne(bookId);
    }

}
