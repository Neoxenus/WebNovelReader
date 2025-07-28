package com.neoxenus.webnovelreader.bookmarkcollection.service.impl;

import com.neoxenus.webnovelreader.bookmark.dto.BookmarkDto;
import com.neoxenus.webnovelreader.bookmark.entity.Bookmark;
import com.neoxenus.webnovelreader.bookmark.mapper.BookmarkMapper;
import com.neoxenus.webnovelreader.bookmark.repo.BookmarkRepository;
import com.neoxenus.webnovelreader.bookmarkcollection.dto.BookmarkCollectionDto;
import com.neoxenus.webnovelreader.bookmarkcollection.projection.BookmarkCountProjection;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
                ), 0L
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
        List<BookmarkCountProjection> count = repository.findBookmarkCountsByUserId(currentUser.getId());
        return mapper.toDto(collections, count);
    }

    @Override
    public List<BookmarkDto> loadCollectionContent(Long id) {
        List<Bookmark> bookmarks = bookmarkRepository.findBookmarksByCollectionId(id);
        return bookmarkMapper.toDto(bookmarks);
    }

    @Override
    @Transactional
    public BookmarkCollectionDto updateCollection(Long id, BookmarkCollectionUpdateRequest request) {
        Optional<BookmarkCollection> optionalBookmarkCollection = repository.findById(id);
        if(optionalBookmarkCollection.isPresent()){
            BookmarkCollection collection = optionalBookmarkCollection.get();
            BookmarkCollection updatedCollection = mapper.toCollection(collection, request);
            return mapper.toDto(repository.save(updatedCollection), Long.valueOf(updatedCollection.getBookmarks().size()));
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
    public void emptyCollection(Long id) {
        bookmarkRepository.deleteAllByCollectionId(id);//todo: more tests probably need changes
    }

    @Override
    public void deleteCollection(Long id) {
        repository.deleteById(id); //todo: more tests (with cascade deleting)
    }
}
