package com.neoxenus.webnovelreader.book.service.impl;

import com.neoxenus.webnovelreader.book.dto.response.BookDtoResponse;
import com.neoxenus.webnovelreader.book.dto.response.BookRatingDtoResponse;
import com.neoxenus.webnovelreader.book.dto.request.BookCreateDtoRequest;
import com.neoxenus.webnovelreader.book.dto.request.BookRatingDtoRequest;
import com.neoxenus.webnovelreader.book.dto.request.BookUpdateDtoRequest;
import com.neoxenus.webnovelreader.book.entity.Book;
import com.neoxenus.webnovelreader.book.entity.BookRating;
import com.neoxenus.webnovelreader.book.mapper.BookMapper;
import com.neoxenus.webnovelreader.book.mapper.BookRatingMapper;
import com.neoxenus.webnovelreader.book.repo.BookRatingRepository;
import com.neoxenus.webnovelreader.book.repo.BookRepository;
import com.neoxenus.webnovelreader.book.service.BookService;
import com.neoxenus.webnovelreader.exceptions.NoSuchEntityException;
import com.neoxenus.webnovelreader.tag.entity.Tag;
import com.neoxenus.webnovelreader.user.entity.User;
import com.neoxenus.webnovelreader.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public BookDtoResponse saveBook(BookCreateDtoRequest book) {
        Book savedBook = bookRepository.save(bookMapper.toBook(book));
//        bookRepository.flush();
        //todo: check for uniqueness
        //todo: validation (especially tags validation)
        return bookMapper.toDto(savedBook);
    }

    @Override
    @Transactional
    public BookDtoResponse getBookDtoById(Long id) {
        return bookMapper.toDto(getBookById(id));
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new NoSuchEntityException("No book for an id: " + id));
    }

    @Override
    public Page<BookDtoResponse> getBooks(Pageable pageable) {
        return bookMapper.toDto(
                bookRepository.findAll(pageable)
        );
    }

    @Override
    @Transactional
    public BookDtoResponse updateBook(Long id, BookUpdateDtoRequest bookRequest) {
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
    public BookRatingDtoResponse getRatingForBook(Long id) {
        return ratingMapper.toDto(ratingRepository.getAveragesByBookId(id));
    }

    @Override
    @Transactional
    public BookRatingDtoResponse rateBook(Long id, BookRatingDtoRequest request) {
        User user = userService.getCurrentUser();
        if(user == null) {
            log.error("User must be logged in to rate books");
            throw new AccessDeniedException("User must be logged in to rate books");
        }
        BookRating rating = ratingRepository.findByBookIdAndUserId(id, user.getId());
        Book book = bookRepository.findById(id).orElseThrow(() -> new NoSuchEntityException("No book for this id: " + id));

        boolean isNew = (rating == null);


        if (isNew) {
            rating = ratingMapper.toRating(request);
            rating.setBook(book);
            rating.setUser(user);
        } else {
            rating = ratingMapper.toRating(rating, request);
        }
        ratingRepository.save(rating);

        book.updateAverage(request.getArray(), (isNew ? null : rating));

        bookRepository.save(book);
        return getRatingForBook(id);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            log.info("Deleting book with id: {}", id);
            Book toDelete = book.get();
            for(Tag tag: toDelete.getTags()) {
                tag.getBooks().remove(toDelete);
            }
            bookRepository.delete(toDelete);

        }
        else{
            log.error("No book for this id: {}", id);
            throw new NoSuchEntityException("No book for this id: " + id);
        }
    }
}
