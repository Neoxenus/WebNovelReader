package com.neoxenus.webnovelreader.comment.service.impl;

import com.neoxenus.webnovelreader.comment.entity.Comment;
import com.neoxenus.webnovelreader.comment.dto.request.CommentCreateDtoRequest;
import com.neoxenus.webnovelreader.comment.dto.response.CommentDtoResponse;
import com.neoxenus.webnovelreader.comment.dto.request.CommentUpdateDtoRequest;
import com.neoxenus.webnovelreader.comment.enums.CommentTargetType;
import com.neoxenus.webnovelreader.comment.mapper.CommentMapper;
import com.neoxenus.webnovelreader.comment.repo.CommentRepository;
import com.neoxenus.webnovelreader.comment.service.CommentService;
import com.neoxenus.webnovelreader.comment.service.target.CommentTargetServiceFactory;
import com.neoxenus.webnovelreader.exceptions.NoSuchEntityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImp implements CommentService {
    private final CommentTargetServiceFactory factory;
    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    @Override
    public CommentDtoResponse updateComment(Long commentId, CommentUpdateDtoRequest request) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if(optionalComment.isPresent()){
            Comment commentById = optionalComment.get();
            Comment updatedComment = commentMapper.toComment(commentById, request);
            return commentMapper.toDto(updatedComment);
        } else {
            throw new NoSuchEntityException("No comment for this id: " + commentId);
        }
    }

    @Override
    public void deleteComment(Long commentId) {
        if(commentRepository.existsById(commentId)){
            log.info("Deleting comment with id: {}", commentId);
            commentRepository.deleteById(commentId);
        }
        else{
            log.error("No comment for this id: {}", commentId);
            throw new NoSuchEntityException("No comment for this id: " + commentId);
        }
    }

    @Override
    public List<CommentDtoResponse> getComments(CommentTargetType type, Long targetId) {
        return factory.getService(type).getComments(targetId);
    }

    @Override
    public CommentDtoResponse createComment(CommentTargetType type, Long targetId, CommentCreateDtoRequest request) {
        return factory.getService(type).createComment(targetId, request);
    }

}
