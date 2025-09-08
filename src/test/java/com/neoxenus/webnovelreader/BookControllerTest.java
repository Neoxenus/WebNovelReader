package com.neoxenus.webnovelreader;

import com.neoxenus.webnovelreader.book.dto.BookDto;
import com.neoxenus.webnovelreader.book.dto.request.BookCreateRequest;
import com.neoxenus.webnovelreader.book.service.BookService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookService bookService;

    private BookDto book;

    @BeforeEach
    void setUp() {

        BookCreateRequest request =
                new BookCreateRequest("test book", "1000", 1L, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        book = bookService.saveBook(request);
    }

    @AfterEach
    void cleanUp() {
        bookService.deleteBook(book.id());
    }
    @Test
    void shouldReturnBookById() throws Exception {
        mockMvc.perform(get("/api/books/" + book.id()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(book.title()));
    }
}
