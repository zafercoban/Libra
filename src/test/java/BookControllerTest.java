/**
 * Created by Zafer on 29.10.2015.
 */

import Library.Management.domain.Book;
import Library.Management.repository.BookRepository;


import Library.Management.service.BookService;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.util.CollectionUtils;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
public class BookControllerTest {

    private MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Autowired
    protected BookRepository bookRepository;
    @Autowired
    protected BookService service;


    @Before
    public void init() {
        this.mockMvc = webAppContextSetup(this.webApplicationContext).build();
    }


    @Test
    @DirtiesContext
    public void shouldSaveBook() throws Exception {
        mockMvc.perform((post("/books/create").contentType(MediaType.MULTIPART_FORM_DATA).param("bookname", "zafer"))
                .param("author", "author").param("status", "Active"))
                .andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    public void shouldGetBooksList() throws Exception {

        mockMvc.perform(get("/books/records")).andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    public void shouldUpdateBookTest() throws Exception {

        List<Book> bookList = this.bookRepository.findAll();
        if (!CollectionUtils.isEmpty(bookList)) {
            Book book = bookList.get(0);

            book.getId();
            book.setBookName("TEST");
            book.setAuthor("denee");
            ObjectMapper objectMapper = new ObjectMapper();
            String content = objectMapper.writeValueAsString(book);

            mockMvc.perform(post("/books/update/{id=}",book.getId()).content(content).contentType(MediaType.MULTIPART_FORM_DATA)).andExpect(status().isOk());

        }
    }

    @Test
    @DirtiesContext

    public void shouldDeleteBookTest() throws Exception {
        List<Book> bookList = this.bookRepository.findAll();
        if (!CollectionUtils.isEmpty(bookList)) {
            Book book = bookList.get(0);
            book.getId();
            mockMvc.perform(delete("books/delete").contentType(MediaType.MULTIPART_FORM_DATA).param("id", book.getId()));

        }
    }

    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    public void setWebApplicationContext(WebApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;
    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

}
