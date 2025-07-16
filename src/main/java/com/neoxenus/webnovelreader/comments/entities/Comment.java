package com.neoxenus.webnovelreader.comments.entities;


import com.neoxenus.webnovelreader.books.entities.Book;
import com.neoxenus.webnovelreader.chapters.etitities.Chapter;
import com.neoxenus.webnovelreader.user.entities.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Comment {



    /*
        comment{
            id (PK)
            user_id (FK)
            book_id (FK, nullable)
            chapter_id (FK, nullable)
            content
            created_at
            ----------------------
            todo: votes
            @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
            private List<CommentVote> votes = new ArrayList<>();
        }
     */
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @ManyToOne
    private User user;

    @Column(nullable = true)
    @ManyToOne
    private Book book;

    @Column(nullable = true)
    @ManyToOne
    private Chapter chapter;

    private String content;

    @CreationTimestamp
    private LocalDateTime createdAt;

//    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<CommentVote> votes = new ArrayList<>();
}
