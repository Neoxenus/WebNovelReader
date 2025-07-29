package com.neoxenus.webnovelreader.bookmarkcollection.mapper.impl;

import com.neoxenus.webnovelreader.bookmarkcollection.dto.BookmarkCollectionDto;
import com.neoxenus.webnovelreader.bookmarkcollection.dto.request.BookmarkCollectionCreateRequest;
import com.neoxenus.webnovelreader.bookmarkcollection.dto.request.BookmarkCollectionUpdateRequest;
import com.neoxenus.webnovelreader.bookmarkcollection.entity.BookmarkCollection;
import com.neoxenus.webnovelreader.bookmarkcollection.mapper.BookmarkCollectionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookmarkCollectionMapperImpl implements BookmarkCollectionMapper {

    @Override
    public BookmarkCollectionDto toDto(BookmarkCollection collection) {

        return BookmarkCollectionDto.builder()
                .id(collection.getId())
                .isDefault(collection.getIsDefault())
                .isPublic(collection.getIsPublic())
                .userId(collection.getUser().getId())
                .name(collection.getName())
                .description(collection.getDescription())
                .position(collection.getPosition())
                .count(collection.getCount())
                .build();
    }

    @Override
    public List<BookmarkCollectionDto> toDto(List<BookmarkCollection> collections) {
        return collections.stream().map( this::toDto).toList();
    }

    @Override
    public BookmarkCollection toCollection(BookmarkCollectionCreateRequest request) {

        BookmarkCollection collection = new BookmarkCollection();
        collection.setIsDefault(false);
        collection.setIsPublic(request.isPublic());
        collection.setName(request.name());
        collection.setDescription(request.description());
        collection.setBookmarks(new ArrayList<>());
        collection.setCount(0);
        return collection;
    }

    @Override
    public BookmarkCollection toCollection(BookmarkCollection toUpdate, BookmarkCollectionUpdateRequest request) {
        if(toUpdate == null || request == null)
            return null;
        toUpdate.setIsPublic(request.isPublic());
        toUpdate.setName(request.name());
        toUpdate.setDescription(request.description());
        return toUpdate;
    }
}
