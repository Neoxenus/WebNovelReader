package com.neoxenus.webnovelreader.bookmark.entity;

import com.neoxenus.webnovelreader.book.entity.Book;
import com.neoxenus.webnovelreader.bookmark.enums.BookmarkType;
import com.neoxenus.webnovelreader.bookmarkcollection.entity.BookmarkCollection;
import com.neoxenus.webnovelreader.chapter.etitity.Chapter;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Bookmark {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    private Book book;

    @Enumerated(EnumType.STRING)
    private BookmarkType type;

    @ManyToOne
    private Chapter chapter; // Optional (only for chapter type bookmarks)

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "bookmark_collection_link",
            joinColumns = @JoinColumn(name = "bookmark_id"),
            inverseJoinColumns = @JoinColumn(name = "collection_id")
    )
    private List<BookmarkCollection> collection = new ArrayList<>();

    private String note; // only for book's bookmarks

    @CreationTimestamp
    private LocalDateTime createdAt;

}
