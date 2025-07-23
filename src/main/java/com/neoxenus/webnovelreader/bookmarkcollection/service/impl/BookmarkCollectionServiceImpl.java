package com.neoxenus.webnovelreader.bookmarkcollection.service.impl;

import com.neoxenus.webnovelreader.bookmark.dto.BookmarkDto;
import com.neoxenus.webnovelreader.bookmarkcollection.dto.BookmarkCollectionDto;
import com.neoxenus.webnovelreader.bookmarkcollection.dto.projection.BookmarkCountProjection;
import com.neoxenus.webnovelreader.bookmarkcollection.dto.request.BookmarkCollectionCreateRequest;
import com.neoxenus.webnovelreader.bookmarkcollection.dto.request.BookmarkCollectionUpdateRequest;
import com.neoxenus.webnovelreader.bookmarkcollection.dto.request.CollectionReorderRequest;
import com.neoxenus.webnovelreader.bookmarkcollection.entity.BookmarkCollection;
import com.neoxenus.webnovelreader.bookmarkcollection.mapper.BookmarkCollectionMapper;
import com.neoxenus.webnovelreader.bookmarkcollection.repo.BookmarkCollectionRepository;
import com.neoxenus.webnovelreader.bookmarkcollection.service.BookmarkCollectionService;
import com.neoxenus.webnovelreader.user.entity.User;
import com.neoxenus.webnovelreader.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookmarkCollectionServiceImpl implements BookmarkCollectionService {
    private final BookmarkCollectionRepository repository;
    private final UserService userService;
    private final BookmarkCollectionMapper mapper;


    @Override
    public BookmarkCollectionDto createCollection(BookmarkCollectionCreateRequest request) {
        User currentUser = userService.getCurrentUser();
        BookmarkCollection collection = mapper.toBookmark(request);
        collection.setUser(currentUser);
        collection.setPosition(repository.findMaxPositionByUserId(currentUser.getId()).orElse(0) + 1);
        return mapper.toDto(
                repository.save(
                        collection
                ), 0L
        );
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
        return null;
    }

    @Override
    public BookmarkCollectionDto updateCollection(Long id, BookmarkCollectionUpdateRequest request) {
        return null;
    }

    @Override
    public List<BookmarkCollectionDto> reorder(List<CollectionReorderRequest> request) {
        return null;
    }

    @Override
    public void emptyCollection(Long id) {

    }

    @Override
    public void deleteCollection(Long id) {

    }
}
