package com.neoxenus.webnovelreader.chapters.etitities;

import com.neoxenus.webnovelreader.books.entities.Book;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Chapter {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    //@JsonBackReference
    private Book book;

    private String title;

    private Integer chapterNumber;

    @OneToOne(mappedBy = "chapter", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private ChapterContent content;

//    private LocalDateTime createdAt;
    @CreationTimestamp
    private LocalDateTime datePublished;

    //private List<Comment> commentList;

    //private T views;


}
