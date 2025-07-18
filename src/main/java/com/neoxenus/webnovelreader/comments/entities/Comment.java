package com.neoxenus.webnovelreader.comments.entities;


import com.neoxenus.webnovelreader.books.entities.Book;
import com.neoxenus.webnovelreader.chapters.etitities.Chapter;
import com.neoxenus.webnovelreader.user.entities.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Comment {



    /*
        comment{
            ...
            ----------------------
            todo: votes
            @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
            private List<CommentVote> votes = new ArrayList<>();
        }
     */
    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn(nullable = false)
    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Chapter chapter;

    private String content;

    @ManyToOne
    private Comment parent;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Comment> replies = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;


//    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<CommentVote> votes = new ArrayList<>();
}
