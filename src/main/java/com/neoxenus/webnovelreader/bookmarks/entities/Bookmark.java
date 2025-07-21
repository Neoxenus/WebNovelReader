package com.neoxenus.webnovelreader.bookmarks.entities;

import com.neoxenus.webnovelreader.books.entities.Book;
import com.neoxenus.webnovelreader.chapters.etitities.Chapter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

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

    @ManyToOne(optional = false)
    private BookmarkCollection collection;

    private String note;

}
