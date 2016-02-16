package Library.Management.controller;

import Library.Management.domain.Book;
import Library.Management.dto.BookListDto;
import Library.Management.service.BookService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/books")
public class BookController {


    @Autowired
    private BookService service;

    @RequestMapping(value = "/records")
    public BookListDto getBooks() {

        BookListDto bookListDto = new BookListDto();
        bookListDto.setBooks(service.readAll());
        return bookListDto;
    }

    @RequestMapping(value = "/get")
    public Book get(@RequestBody Book book) {
        return service.read(book);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Book create(
            @RequestParam(required = false) String bookName,
            @RequestParam String author) {

        Book newBook = new Book();
        newBook.setBookName(bookName);
        newBook.setAuthor(author);
        return service.create(newBook);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Book update(
            @RequestParam String id,
            @RequestParam String bookName,
            @RequestParam String author) {


        Book existingBook = new Book();
        existingBook.setId(id);
        existingBook.setBookName(bookName);
        existingBook.setAuthor(author);

        return service.update(existingBook);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Boolean delete(
            @RequestParam String id) {

        if (StringUtils.isBlank(id)) {
            return false;
        }

        Book persistedBook = service.findById(id);
        if (persistedBook == null) {
            return false;
        }

        return service.delete(persistedBook);
    }
}
