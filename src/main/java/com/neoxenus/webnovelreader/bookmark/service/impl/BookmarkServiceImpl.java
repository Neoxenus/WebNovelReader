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
import com.neoxenus.webnovelreader.exceptions.BookmarkDeletedException;
import com.neoxenus.webnovelreader.exceptions.EntityAlreadyExistsException;
import com.neoxenus.webnovelreader.exceptions.NoSuchEntityException;
import com.neoxenus.webnovelreader.user.entity.User;
import com.neoxenus.webnovelreader.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
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
    private final UserService userService;
    private final ChapterService chapterService;
    private final BookmarkCollectionService collectionService;


    @Override
    @Transactional
    public BookmarkDto createBookmark(BookmarkCreateRequest request) {
        collectionService.verifyUserAccessToBookmarkCollection(request.collectionId());
        verifyBookmarkDoesNotExists(request, userService.getCurrentUser().getId());
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

    @Transactional(readOnly = true)
    public void verifyBookmarkDoesNotExists(BookmarkCreateRequest request, Long userId){
        boolean isExist = switch(request.type()){
            case BOOK ->
                    bookmarkRepository
                            .existsByBookIdAndChapterNullAndCollectionsUserId(
                                    request.bookId(),
                                    userId);
            case CHAPTER ->
                    bookmarkRepository
                            .existsByBookIdAndChapterIdAndCollectionsUserId(
                                    request.bookId(),
                                    request.chapterId(),
                                    userId);
        };
        if(isExist){
            log.error(
                    "Bookmark for book {} and chapter {} and user {} already exists",
                    request.bookId(),
                    request.chapterId(),
                    userId
            );
            throw new EntityAlreadyExistsException(
                    "Bookmark for book "
                            + request.bookId()
                            + " and chapter "
                            + request.chapterId()
                            + " and user "
                            + userId
                            + " already exists");
        }
    }
    @Override
    @Transactional(noRollbackFor = BookmarkDeletedException.class)
    public BookmarkDto updateBookmark(Long id, BookmarkUpdateRequest request) {
        Bookmark toUpdate = verifyUserAccessToBookmark(id);

        Bookmark updatedBookmark = mapper.toBookmark(toUpdate, request);
        if(request.type() != UpdateType.NOTE) {
            handleCollectionUpdate(request, updatedBookmark, id);
        }

        return mapper.toDto(bookmarkRepository.save(updatedBookmark));
    }

    private void handleCollectionUpdate(BookmarkUpdateRequest request, Bookmark updated, Long bookmarkId) {
        collectionService.verifyUserAccessToBookmarkCollection(request.collectionId());

        List<BookmarkCollection> collectionList = updated.getCollections();

        if(request.type().equals(UpdateType.ADD)){
            BookmarkCollection collection = collectionService.updateCount(request.collectionId(), 1);
            collectionList.add(collection);
        } else if(request.type().equals(UpdateType.REMOVE)){
            BookmarkCollection collection = collectionService.getCollectionById(request.collectionId());
            if(collectionList.remove(collection)) {
                collectionService.updateCount(request.collectionId(),-1);
            } else {
                log.warn("Bookmark {} does not belong to collection {}", bookmarkId, request.collectionId());
            }
            if(collectionList.isEmpty()) {
                bookmarkRepository.deleteById(bookmarkId);
                bookmarkRepository.flush();
                throw new BookmarkDeletedException();
            }
        }

        updated.setCollections(collectionList);
    }

    @Override
    public BookmarkDto getBookmark(Long id) {
        verifyUserAccessToBookmark(id);
        return mapper.toDto(verifyUserAccessToBookmark(id));
    }

    @Override
    public Bookmark findById(Long id) {
        return bookmarkRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException("No bookmark with an id: " + id));
    }

    @Override
    public Bookmark verifyUserAccessToBookmark(Long bookmarkId) {
        Bookmark bookmark = findById(bookmarkId);
        User currentUser = userService.getCurrentUser();

        List<BookmarkCollection> collections = bookmark.getCollections();
        if (collections.isEmpty() || collections.stream().noneMatch(e -> e.getUser().getId().equals(currentUser.getId()))) {
            log.error("User dont have access to bookmark {}", bookmarkId);
            throw new AccessDeniedException("User dont have access to bookmark " + bookmarkId);
        }
        log.debug("Access granted: User {} have access to bookmark {}",currentUser.getId(), bookmarkId);
        return bookmark;
    }
}
