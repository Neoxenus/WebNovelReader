package com.neoxenus.webnovelreader.comment.entity;


import com.neoxenus.webnovelreader.book.entity.Book;
import com.neoxenus.webnovelreader.chapter.etitity.Chapter;
import com.neoxenus.webnovelreader.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "cooments")
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

    private Long likesCount;

    private Long dislikesCount;

}
