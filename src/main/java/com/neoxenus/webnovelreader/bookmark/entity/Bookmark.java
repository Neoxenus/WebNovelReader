package com.neoxenus.webnovelreader.bookmark.entity;

import com.neoxenus.webnovelreader.book.entity.Book;
import com.neoxenus.webnovelreader.chapter.etitity.Chapter;
import jakarta.persistence.*;
import lombok.Data;

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

    @ManyToOne
    private Chapter chapter; // Optional

    @ManyToMany(fetch = FetchType.EAGER)
    private List<BookmarkCollection> collection = new ArrayList<>();

    private String note; // only for book bookmarks

}
