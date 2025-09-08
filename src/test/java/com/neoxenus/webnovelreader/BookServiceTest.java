package com.neoxenus.webnovelreader;

import com.neoxenus.webnovelreader.book.entity.Book;
import com.neoxenus.webnovelreader.book.repo.BookRepository;
import com.neoxenus.webnovelreader.book.service.impl.BookServiceImpl;
import com.neoxenus.webnovelreader.exceptions.NoSuchEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book();
        book.setId(1L);
        book.setTitle("test book");
        book.setYearOfPublishing("2025");
        book.setChapterList(new ArrayList<>());
    }


    @Test
    void getBookByIdPositive() {
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));

        assertEquals(book, bookService.getBookById(book.getId()));
    }

    @Test
    void getBookByIdNegative() {
        when(bookRepository.findById(book.getId())).thenReturn(Optional.empty());

        assertThrows(NoSuchEntityException.class, () -> bookService.getBookById(1L));
    }
}


