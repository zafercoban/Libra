
import Library.Management.domain.Book;

import Library.Management.repository.BookRepository;
import Library.Management.service.BookService;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void shouldGetAllBookList() {
        Book book1 = new Book();
        book1.setId("1");
        Book book2 = new Book();
        book2.setId("2");
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        List<Book> bookList = bookService.readAll();

        assertThat(bookList.size(), equalTo(2));
        assertThat(bookList.get(0).getId(), equalTo("1"));
        assertThat(bookList.get(1).getId(), equalTo("2"));
    }

    @Test
    public void shouldSaveTest() {
        Book book = new Book();
        book.setBookName("test");
        when(bookRepository.save(book)).thenReturn(book);

        bookService.create(book);
        assertThat(book.getBookName(), equalTo("test"));
        assertNotNull(book.getId());

    }

    @Test
    public void shouldUpdateBook() {
        Book book = new Book();
        book.getId();
        book.setBookName("Test for mockito");
        book.setAuthor("Test for mockito");
        bookRepository.delete(book.getId());
        bookService.update(book);
        ArgumentCaptor<Book> bookCaptor = ArgumentCaptor.forClass(Book.class);

        verify(bookRepository).save(bookCaptor.capture());

        Book capturedBook = bookCaptor.getValue();
        assertThat(capturedBook.getBookName(), equalTo("Test for mockito"));
        assertThat(capturedBook.getAuthor(), equalTo("Test for mockito"));
    }

    @Test
    public void shouldDeleteBook() {
        Book book = new Book();
        book.setBookName("kitap");

        bookService.delete(book);

        ArgumentCaptor<Book> bookCaptor = ArgumentCaptor.forClass(Book.class);

        verify(bookRepository, times(1)).delete(bookCaptor.capture());
        assertThat(bookCaptor.getValue().getBookName(), equalTo("kitap"));

    }
}



