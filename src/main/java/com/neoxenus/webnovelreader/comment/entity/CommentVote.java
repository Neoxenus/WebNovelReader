package com.neoxenus.webnovelreader.comment.entity;

import com.neoxenus.webnovelreader.comment.enums.VoteType;
import com.neoxenus.webnovelreader.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CommentVote {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Comment comment;

    @Enumerated(EnumType.STRING)
    private VoteType vote;
}
