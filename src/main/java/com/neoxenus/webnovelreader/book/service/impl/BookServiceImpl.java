package com.neoxenus.webnovelreader.book.service.impl;

import com.neoxenus.webnovelreader.book.entity.Book;
import com.neoxenus.webnovelreader.book.dto.request.BookCreateRequest;
import com.neoxenus.webnovelreader.book.dto.BookDto;
import com.neoxenus.webnovelreader.book.dto.request.BookUpdateRequest;
import com.neoxenus.webnovelreader.book.mapper.BookMapper;
import com.neoxenus.webnovelreader.book.repo.BookRepository;
import com.neoxenus.webnovelreader.book.service.BookService;
import com.neoxenus.webnovelreader.exceptions.NoSuchEntityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    @Transactional
    public BookDto saveBook(BookCreateRequest book) {
        return bookMapper.toDto(
                bookRepository.save(
                        bookMapper.toBook(book)
                )
        );
    }

    @Override
    @Transactional
    public BookDto getBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new NoSuchEntityException("No book for an id: " + id)
        );
        return bookMapper.toDto(book);
    }

    @Override
    public List<BookDto> getAllBooks() {
        return bookMapper.toDto(bookRepository.findAll());
    }

    @Override
    @Transactional
    public BookDto updateBook(Long id, BookUpdateRequest bookRequest) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {

            //todo: check unique fields
            Book bookById = optionalBook.get();
            Book updatedBook = bookMapper.toBook(bookById, bookRequest);
            return bookMapper.toDto(bookRepository.save(updatedBook));
        } else {
            throw new NoSuchEntityException("No book for this id: " + id);
        }
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        if(bookRepository.existsById(id)){
            log.info("Deleting book with id: {}", id);
            bookRepository.deleteById(id);
        }
        else{
            log.error("No book for this id: {}", id);
            throw new NoSuchEntityException("No book for this id: " + id);
        }
    }
}
