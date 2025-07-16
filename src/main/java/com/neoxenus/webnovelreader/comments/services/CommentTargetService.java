package com.neoxenus.webnovelreader.comments.services;

import com.neoxenus.webnovelreader.comments.entities.Comment;
import com.neoxenus.webnovelreader.comments.entities.dtos.CommentCreateRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public interface CommentTargetService {
    List<Comment> getComments(Long targetId, Pageable pageable);
    Comment createComment(Long targetId, CommentCreateRequest request);

}
