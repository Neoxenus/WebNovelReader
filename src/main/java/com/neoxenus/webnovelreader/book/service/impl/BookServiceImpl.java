package com.neoxenus.webnovelreader.book.service.impl;

import com.neoxenus.webnovelreader.book.dto.BookDto;
import com.neoxenus.webnovelreader.book.dto.BookRatingDto;
import com.neoxenus.webnovelreader.book.dto.request.BookCreateRequest;
import com.neoxenus.webnovelreader.book.dto.request.BookRatingRequest;
import com.neoxenus.webnovelreader.book.dto.request.BookUpdateRequest;
import com.neoxenus.webnovelreader.book.entity.Book;
import com.neoxenus.webnovelreader.book.entity.BookRating;
import com.neoxenus.webnovelreader.book.mapper.BookMapper;
import com.neoxenus.webnovelreader.book.mapper.BookRatingMapper;
import com.neoxenus.webnovelreader.book.repo.BookRatingRepository;
import com.neoxenus.webnovelreader.book.repo.BookRepository;
import com.neoxenus.webnovelreader.book.service.BookService;
import com.neoxenus.webnovelreader.exceptions.NoSuchEntityException;
import com.neoxenus.webnovelreader.user.entity.User;
import com.neoxenus.webnovelreader.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookRatingRepository ratingRepository;
    private final UserService userService;
    private final BookMapper bookMapper;
    private final BookRatingMapper ratingMapper;

    @Override
    @Transactional
    public BookDto saveBook(BookCreateRequest book) {

        //todo: validation (especially tags validation)
        return bookMapper.toDto(
                bookRepository.save(
                        bookMapper.toBook(book)
                ), null
        );
    }

    @Override
    @Transactional
    public BookDto getBookDtoById(Long id) {
        return bookMapper.toDto(getBookById(id), getRatingForBook(id));
    }

    @Override
    public Book getBookById(Long id) {
        return  bookRepository.findById(id).orElseThrow(
                () -> new NoSuchEntityException("No book for an id: " + id)
        );
    }

    @Override
    public List<BookDto> getAllBooks() {
        return bookMapper.toDto(

                bookRepository.findAll(), ratingRepository.getAllAverageRatings()

        );
    }

    @Override
    @Transactional
    public BookDto updateBook(Long id, BookUpdateRequest bookRequest) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {

            //todo: check unique fields
            Book bookById = optionalBook.get();
            Book updatedBook = bookMapper.toBook(bookById, bookRequest);
            BookRatingDto rating = ratingMapper.toDto(ratingRepository.getAveragesByBookId(id));
            return bookMapper.toDto(bookRepository.save(updatedBook), rating);
        } else {
            throw new NoSuchEntityException("No book for this id: " + id);
        }
    }

    @Override
    public BookRatingDto getRatingForBook(Long id) {
        return ratingMapper.toDto(ratingRepository.getAveragesByBookId(id));
    }

    @Override
    @Transactional
    public BookRatingDto rateBook(Long id, BookRatingRequest request) {
        User user = userService.getCurrentUser();
        if(user == null) {
            log.error("User must be logged in to rate books");
            throw new AccessDeniedException("User must be logged in to rate books");
        }
        BookRating rating = ratingRepository.findByBookIdAndUserId(id, user.getId());
        if(rating == null) {
            rating = ratingMapper.toRating(request);
            Book book = bookRepository.findById(id).orElseThrow(() -> new NoSuchEntityException("No book for this id: " + id));
            rating.setBook(book);
            rating.setUser(user);
        } else {
            rating = ratingMapper.toRating(rating, request);
        }
        ratingRepository.save(rating);
        return getRatingForBook(id);
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
