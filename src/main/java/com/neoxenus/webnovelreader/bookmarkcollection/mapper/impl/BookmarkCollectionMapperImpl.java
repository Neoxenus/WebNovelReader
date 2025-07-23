package com.neoxenus.webnovelreader.bookmarkcollection.mapper.impl;

import com.neoxenus.webnovelreader.bookmarkcollection.dto.BookmarkCollectionDto;
import com.neoxenus.webnovelreader.bookmarkcollection.dto.projection.BookmarkCountProjection;
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
    public BookmarkCollectionDto toDto(BookmarkCollection collection, Long count) {

        return BookmarkCollectionDto.builder()
                .id(collection.getId())
                .isDefault(collection.getIsDefault())
                .isPublic(collection.getIsPublic())
                .userId(collection.getUser().getId())
                .name(collection.getName())
                .description(collection.getDescription())
                .position(collection.getPosition())
                .count(count)
                .build();
    }

    @Override
    public List<BookmarkCollectionDto> toDto(List<BookmarkCollection> collections, List<BookmarkCountProjection> countProjections) {
        return collections.stream()
                .map( collection -> {
                            Long count = countProjections.stream()
                                    .filter(p -> p.getId().equals(collection.getId()))
                                    .findFirst()
                                    .map(p -> p.getCount() != null ? p.getCount() : 0L)
                                    .orElse(0L);

                            return toDto(collection, count);
                        })
                        .toList();
    }

    @Override
    public BookmarkCollection toBookmark(BookmarkCollectionCreateRequest request) {

        BookmarkCollection collection = new BookmarkCollection();
        collection.setIsDefault(false);
        collection.setIsPublic(request.isPublic());
        collection.setName(request.name());
        collection.setDescription(request.description());
        collection.setBookmarks(new ArrayList<>());
        return collection;
    }

    @Override
    public BookmarkCollection toBookmark(BookmarkCollection toUpdate, BookmarkCollectionUpdateRequest request) {
        return null;
    }
}
