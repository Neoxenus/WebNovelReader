package com.neoxenus.webnovelreader.comments.services.target.impl;

import com.neoxenus.webnovelreader.comments.entities.Comment;
import com.neoxenus.webnovelreader.comments.entities.dtos.CommentCreateRequest;
import com.neoxenus.webnovelreader.comments.entities.dtos.CommentDto;
import com.neoxenus.webnovelreader.comments.enums.CommentTargetType;
import com.neoxenus.webnovelreader.comments.mapper.CommentMapper;
import com.neoxenus.webnovelreader.comments.repo.CommentRepository;
import com.neoxenus.webnovelreader.comments.services.target.CommentTargetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCommentService implements CommentTargetService {
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    @Override
    public CommentTargetType getTargetType() {
        return CommentTargetType.USER;
    }

    @Override
    public List<CommentDto> getComments(Long targetId) {
        List<Comment> comments = commentRepository.findAllByUserId(targetId);
        return commentMapper.toDtoWithoutReplies(comments);
    }

    @Override
    public CommentDto createComment(Long targetId, CommentCreateRequest request) {
        throw new UnsupportedOperationException("You can't create comments ON a user");
    }
}
