package Library.Management.dto;

import java.io.Serializable;
import java.util.List;

import Library.Management.domain.Book;

public class BookListDto implements Serializable{

    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
