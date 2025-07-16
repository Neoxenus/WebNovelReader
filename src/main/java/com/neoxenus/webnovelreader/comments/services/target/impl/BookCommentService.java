package com.neoxenus.webnovelreader.comments.services.target.impl;

import com.neoxenus.webnovelreader.comments.entities.dtos.CommentCreateRequest;
import com.neoxenus.webnovelreader.comments.entities.dtos.CommentDto;
import com.neoxenus.webnovelreader.comments.enums.CommentTargetType;
import com.neoxenus.webnovelreader.comments.services.target.CommentTargetService;

import java.util.List;

public class BookCommentService implements CommentTargetService {
    @Override
    public CommentTargetType getTargetType() {
        return CommentTargetType.BOOK;
    }

    @Override
    public List<CommentDto> getComments(Long targetId) {
        return null;
    }

    @Override
    public CommentDto createComment(Long targetId, CommentCreateRequest request) {
        return null;
    }
}
