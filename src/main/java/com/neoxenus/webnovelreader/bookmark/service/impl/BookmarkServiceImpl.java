package com.neoxenus.webnovelreader.bookmark.service.impl;

import com.neoxenus.webnovelreader.book.entity.Book;
import com.neoxenus.webnovelreader.book.service.BookService;
import com.neoxenus.webnovelreader.bookmark.dto.BookmarkDto;
import com.neoxenus.webnovelreader.bookmark.dto.request.BookmarkCreateRequest;
import com.neoxenus.webnovelreader.bookmark.dto.request.BookmarkUpdateRequest;
import com.neoxenus.webnovelreader.bookmark.entity.Bookmark;
import com.neoxenus.webnovelreader.bookmark.enums.BookmarkType;
import com.neoxenus.webnovelreader.bookmark.enums.UpdateType;
import com.neoxenus.webnovelreader.bookmark.mapper.BookmarkMapper;
import com.neoxenus.webnovelreader.bookmark.repo.BookmarkRepository;
import com.neoxenus.webnovelreader.bookmark.service.BookmarkService;
import com.neoxenus.webnovelreader.bookmarkcollection.entity.BookmarkCollection;
import com.neoxenus.webnovelreader.bookmarkcollection.service.BookmarkCollectionService;
import com.neoxenus.webnovelreader.chapter.etitity.Chapter;
import com.neoxenus.webnovelreader.chapter.service.ChapterService;
import com.neoxenus.webnovelreader.exceptions.NoSuchEntityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookmarkServiceImpl implements BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final BookmarkMapper mapper;

    private final BookService bookService;
    private final ChapterService chapterService;
    private final BookmarkCollectionService collectionService;


    @Override
    @Transactional
    public BookmarkDto createBookmark(BookmarkCreateRequest request) {
        log.info("Saving bookmark {}", request);

        Book book = bookService.getBookById(request.bookId());
        Chapter chapter = null;
        if(request.type().equals(BookmarkType.CHAPTER)) {
            chapter = chapterService.getChapter(request.bookId(), request.chapterId());
        }
        Bookmark bookmark = mapper.toBookmark(request);
        bookmark.setBook(book);
        bookmark.setChapter(chapter);
        bookmark.setCollections(List.of(collectionService.updateCount(request.collectionId(), 1)));
        bookmark = bookmarkRepository.save(bookmark);
        return mapper.toDto(bookmark);
    }

    @Override
    @Transactional
    public BookmarkDto updateBookmark(Long id, BookmarkUpdateRequest request) {
        Bookmark toUpdate = findById(id);
        Bookmark updatedBookmark = mapper.toBookmark(toUpdate, request);
        if(request.type() != UpdateType.NOTE) {


            List<BookmarkCollection> collectionList = updatedBookmark.getCollections();

            if(request.type().equals(UpdateType.ADD)){
                BookmarkCollection collection = collectionService.updateCount(request.collectionId(), 1);
                collectionList.add(collection);
            } else if(request.type().equals(UpdateType.REMOVE)){
                BookmarkCollection collection = collectionService.getCollectionById(request.collectionId());
                if(collectionList.remove(collection)) {
                     collectionService.updateCount(request.collectionId(),-1);
                } else {
                    log.warn("Bookmark {} does not belong to collection {}", id, request.collectionId());
                }
            }

            updatedBookmark.setCollections(collectionList);
        }

        return mapper.toDto(updatedBookmark);
    }

    @Override
    public BookmarkDto getBookmark(Long id) {
        return mapper.toDto(findById(id));
    }

    @Override
    public Bookmark findById(Long id) {
        return bookmarkRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException("No bookmark with an id: " + id));
    }
}
