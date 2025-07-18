package com.neoxenus.webnovelreader.comments.services.target.impl;

import com.neoxenus.webnovelreader.comments.entities.dtos.CommentCreateRequest;
import com.neoxenus.webnovelreader.comments.entities.dtos.CommentDto;
import com.neoxenus.webnovelreader.comments.enums.CommentTargetType;
import com.neoxenus.webnovelreader.comments.services.target.CommentTargetService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCommentService implements CommentTargetService {
    @Override
    public CommentTargetType getTargetType() {
        return CommentTargetType.USER;
    }

    @Override
    public List<CommentDto> getComments(Long targetId) {
        //set replies to null or empy list before converting it to dtos
        return null;
    }

    @Override
    public CommentDto createComment(Long targetId, CommentCreateRequest request) {
        throw new UnsupportedOperationException("You can't create comments ON a user");
    }
}
