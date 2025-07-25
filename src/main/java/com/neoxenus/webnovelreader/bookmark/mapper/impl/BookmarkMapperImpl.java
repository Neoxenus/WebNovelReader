package com.neoxenus.webnovelreader.bookmark.mapper.impl;

import com.neoxenus.webnovelreader.book.mapper.BookMapper;
import com.neoxenus.webnovelreader.bookmark.dto.BookmarkDto;
import com.neoxenus.webnovelreader.bookmark.dto.request.BookmarkCreateRequest;
import com.neoxenus.webnovelreader.bookmark.dto.request.BookmarkUpdateRequest;
import com.neoxenus.webnovelreader.bookmark.entity.Bookmark;
import com.neoxenus.webnovelreader.bookmark.mapper.BookmarkMapper;
import com.neoxenus.webnovelreader.bookmarkcollection.entity.BookmarkCollection;
import com.neoxenus.webnovelreader.chapter.mapper.ChapterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookmarkMapperImpl implements BookmarkMapper {

    private final BookMapper bookMapper;
    private final ChapterMapper chapterMapper;

//    Long id,
//    BookDto book,
//    ChapterDto chapter,
    //type
//    List<long> collectionId,

//    String note) {


    //------------------------
//    @Id
//    @GeneratedValue
//    private Long id;
//
//    @ManyToOne(optional = false)
//    private Book book;
//
//    @Enumerated(EnumType.STRING)
//    private BookmarkType type;
//
//    @ManyToOne
//    private Chapter chapter; // Optional (only for chapter type bookmarks)
//
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "bookmark_collection_link",
//            joinColumns = @JoinColumn(name = "bookmark_id"),
//            inverseJoinColumns = @JoinColumn(name = "collection_id")
//    )
//    private List<BookmarkCollection> collection = new ArrayList<>();
//
//    private String note; // only for book's bookmarks
//
//    @CreationTimestamp
//    private LocalDateTime createdAt;
    @Override
    public BookmarkDto toDto(Bookmark bookmark) {
        return BookmarkDto.builder()
                .id(bookmark.getId())
                .book(bookMapper.toDto(bookmark.getBook()))
                .chapter(chapterMapper.toDto(bookmark.getChapter()))
                .type(bookmark.getType())
                .collectionId(bookmark.getCollection().stream().map(BookmarkCollection::getId).toList())
                .note(bookmark.getNote())
                .createdAt(bookmark.getCreatedAt())
                .build();
    }

    @Override
    public List<BookmarkDto> toDto(List<Bookmark> bookmark) {
        return bookmark.stream().map(this::toDto).toList();
    }

    @Override
    public Bookmark toBookmark(BookmarkCreateRequest request) {
        Bookmark bookmark = new Bookmark();
        bookmark.setType(request.type());
        bookmark.setNote(request.note());
        return bookmark;
    }

    @Override
    public Bookmark toBookmark(Bookmark toUpdate, BookmarkUpdateRequest request) {
        if(toUpdate == null || request == null)
            return null;
        toUpdate.setNote(request.note());
        return toUpdate;
    }
}
