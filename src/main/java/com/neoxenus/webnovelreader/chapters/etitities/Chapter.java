package com.neoxenus.webnovelreader.chapters.etitities;

import com.neoxenus.webnovelreader.books.entities.Book;
import jakarta.persistence.*;
import lombok.Data;

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

    @Lob
    @Basic(fetch = FetchType.LAZY) // Lazy load the large text field
    private String content; // need to choose type carefully and how to store content !!!!!!!!!!

//    private LocalDateTime createdAt;
    private LocalDateTime datePublished;

    //private List<Comment> commentList;

    //private T views;


}
