package com.neoxenus.webnovelreader.bookmarkcollection.service.impl;

import com.neoxenus.webnovelreader.bookmark.dto.BookmarkDto;
import com.neoxenus.webnovelreader.bookmark.entity.Bookmark;
import com.neoxenus.webnovelreader.bookmark.mapper.BookmarkMapper;
import com.neoxenus.webnovelreader.bookmark.repo.BookmarkRepository;
import com.neoxenus.webnovelreader.bookmarkcollection.dto.BookmarkCollectionDto;
import com.neoxenus.webnovelreader.bookmarkcollection.dto.request.BookmarkCollectionCreateRequest;
import com.neoxenus.webnovelreader.bookmarkcollection.dto.request.BookmarkCollectionUpdateRequest;
import com.neoxenus.webnovelreader.bookmarkcollection.dto.request.CollectionReorderRequest;
import com.neoxenus.webnovelreader.bookmarkcollection.entity.BookmarkCollection;
import com.neoxenus.webnovelreader.bookmarkcollection.mapper.BookmarkCollectionMapper;
import com.neoxenus.webnovelreader.bookmarkcollection.repo.BookmarkCollectionRepository;
import com.neoxenus.webnovelreader.bookmarkcollection.service.BookmarkCollectionService;
import com.neoxenus.webnovelreader.exceptions.NoSuchEntityException;
import com.neoxenus.webnovelreader.user.entity.User;
import com.neoxenus.webnovelreader.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookmarkCollectionServiceImpl implements BookmarkCollectionService {
    private final BookmarkCollectionRepository repository;
    private final BookmarkRepository bookmarkRepository;

    private final UserService userService;
    private final BookmarkCollectionMapper mapper;
    private final BookmarkMapper bookmarkMapper;



    @Override
    @Transactional
    public BookmarkCollectionDto createCollection(BookmarkCollectionCreateRequest request) {
        User currentUser = userService.getCurrentUser();
        BookmarkCollection collection = mapper.toCollection(request);
        collection.setUser(currentUser);
        collection.setPosition(repository.findMaxPositionByUserId(currentUser.getId()).orElse(0) + 1);
        return mapper.toDto(
                repository.save(
                        collection
                )
        );
    }

    @Override
    public BookmarkCollection getCollectionById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException("No bookmark collection with id: " + id));
    }

    @Override
    public List<BookmarkCollection> getCollectionById(List<Long> ids) {
        return ids.stream().map(this::getCollectionById).toList();
    }

    @Override
    public List<BookmarkCollectionDto> getCollections() {
        User currentUser = userService.getCurrentUser();
        List<BookmarkCollection> collections = repository.findByUserId(currentUser.getId());
        return mapper.toDto(collections).stream()
                .sorted(Comparator.comparingInt(BookmarkCollectionDto::position)).collect(Collectors.toList());
    }

    @Override
    public List<BookmarkDto> loadCollectionContent(Long id) {
        verifyUserAccessToBookmarkCollection(id);
        List<Bookmark> bookmarks = bookmarkRepository.findBookmarksByCollectionsId(id);
        return bookmarkMapper.toDto(bookmarks);
    }

    @Override
    @Transactional
    public BookmarkCollectionDto updateCollection(Long id, BookmarkCollectionUpdateRequest request) {
        Optional<BookmarkCollection> optionalBookmarkCollection = repository.findById(id);
        if(optionalBookmarkCollection.isPresent()){
            BookmarkCollection collection = optionalBookmarkCollection.get();
            BookmarkCollection updatedCollection = mapper.toCollection(collection, request);
            return mapper.toDto(repository.save(updatedCollection));
        } else {
            //todo: logs
            throw new NoSuchEntityException("No bookmark collection for id: " + id);
        }
    }

    @Override
    public List<BookmarkCollectionDto> reorder(List<CollectionReorderRequest> request) {
        return null;
    }

    @Override
    @Transactional
    public BookmarkCollection updateCount(Long id, int delta) {
        BookmarkCollection collection = this.getCollectionById(id);
        collection.setCount(collection.getCount() + delta);
        return repository.save(collection);
    }

    @Override
    public BookmarkCollection verifyUserAccessToBookmarkCollection(Long collectionId) {
        BookmarkCollection collection = getCollectionById(collectionId);
        User currentUser = userService.getCurrentUser();

        if (!collection.getUser().getId().equals(currentUser.getId())) {
            log.error("User dont have access to bookmark collection {}", collectionId);
            throw new AccessDeniedException("User dont have access to bookmark collection " + collectionId);
        }
        log.debug("Access granted: User {} have access to bookmark collection {}",currentUser.getId(), collectionId);
        return collection;
    }

    @Override
    @Transactional
    public void emptyCollection(Long id) {
        BookmarkCollection collection = verifyUserAccessToBookmarkCollection(id);

        List<Bookmark> bookmarks = bookmarkRepository.findBookmarksByCollectionsId(id);

        bookmarks.forEach(
                e -> {
                    e.getCollections().remove(collection);
                    if(e.getCollections().isEmpty()) {
                        bookmarkRepository.delete(e);
                    } else {
                        bookmarkRepository.save(e);
                    }

                }
        );

        collection.setCount(0);
    }

    @Override
    @Transactional
    public void deleteCollection(Long id) {
        BookmarkCollection collection = verifyUserAccessToBookmarkCollection(id);
        if(collection.getIsDefault()){
            log.error("Attempting to delete default collection");
            throw new AccessDeniedException("User can't delete default collection");
        } else {
            log.info("Deleting collection {}", id);
            emptyCollection(id);
            repository.deleteById(id);
        }
    }

    @Override
    public void initDefaultCollectionsForUser(User user) {
        log.info("Creating default collections for user {}", user.getUsername());
        List<BookmarkCollection> collections = List.of(
                new BookmarkCollection(null, true, false, user, "Viewed", null, 1, new ArrayList<>(), 0),
                new BookmarkCollection(null, true, false, user, "Reading now", null, 2, new ArrayList<>(), 0),
                new BookmarkCollection(null, true, false, user, "Will read", null, 3, new ArrayList<>(), 0),
                new BookmarkCollection(null, true, false, user, "Awaiting", null, 4, new ArrayList<>(), 0),
                new BookmarkCollection(null, true, false, user, "Delayed", null, 5, new ArrayList<>(), 0),
                new BookmarkCollection(null, true, false, user, "Dropped", null, 6, new ArrayList<>(), 0)
                );
        repository.saveAll(collections);
    }
}
