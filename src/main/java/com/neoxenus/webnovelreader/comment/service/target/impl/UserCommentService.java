package com.neoxenus.webnovelreader.comment.service.target.impl;

import com.neoxenus.webnovelreader.comment.entity.Comment;
import com.neoxenus.webnovelreader.comment.dto.request.CommentCreateDtoRequest;
import com.neoxenus.webnovelreader.comment.dto.response.CommentDtoResponse;
import com.neoxenus.webnovelreader.comment.enums.CommentTargetType;
import com.neoxenus.webnovelreader.comment.mapper.CommentMapper;
import com.neoxenus.webnovelreader.comment.repo.CommentRepository;
import com.neoxenus.webnovelreader.comment.service.target.CommentTargetService;
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
    public List<CommentDtoResponse> getComments(Long targetId) {
        List<Comment> comments = commentRepository.findAllByUserId(targetId);
        return commentMapper.toDtoWithoutReplies(comments);
    }

    @Override
    public CommentDtoResponse createComment(Long targetId, CommentCreateDtoRequest request) {
        throw new UnsupportedOperationException("You can't create comments ON a user");
    }
}
