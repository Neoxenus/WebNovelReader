package com.neoxenus.webnovelreader.integration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoxenus.webnovelreader.book.dto.response.BookDtoResponse;
import com.neoxenus.webnovelreader.book.dto.request.BookCreateDtoRequest;
import com.neoxenus.webnovelreader.book.service.BookService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    private BookDtoResponse book;

    private String token = "";

    @BeforeEach
    void setUp() throws Exception {
        String loginResponse = mockMvc.perform(post("/api/login")
                        .param("username", "admin")
                        .param("password", "admin"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode jsonNode = objectMapper.readTree(loginResponse);
        token = jsonNode.get("access_token").asText();


        BookCreateDtoRequest request =
                new BookCreateDtoRequest("test book", "1000", 1L, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
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

    @Test
    void saveBookPositive() throws Exception {


        BookCreateDtoRequest request =
                new BookCreateDtoRequest("test book for post", "1000", 1L, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        mockMvc.perform(post("/api/books")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(request.title()));


    }

    @Test
    void saveBookNegative() throws Exception {


        BookCreateDtoRequest request =
                new BookCreateDtoRequest("test book for post", "1000", 1L, null, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        mockMvc.perform(post("/api/books")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.detail").exists());


    }
}
