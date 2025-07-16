package com.neoxenus.webnovelreader.comments.services.target.impl;

import com.neoxenus.webnovelreader.comments.entities.dtos.CommentCreateRequest;
import com.neoxenus.webnovelreader.comments.entities.dtos.CommentDto;
import com.neoxenus.webnovelreader.comments.enums.CommentTargetType;
import com.neoxenus.webnovelreader.comments.services.target.CommentTargetService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterCommentService implements CommentTargetService {
    @Override
    public CommentTargetType getTargetType() {
        return CommentTargetType.CHAPTER;
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
