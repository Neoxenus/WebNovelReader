package com.neoxenus.webnovelreader.comment.service;

import com.neoxenus.webnovelreader.comment.dto.request.CommentCreateRequest;
import com.neoxenus.webnovelreader.comment.dto.CommentDto;
import com.neoxenus.webnovelreader.comment.dto.request.CommentUpdateRequest;
import com.neoxenus.webnovelreader.comment.enums.CommentTargetType;
import com.neoxenus.webnovelreader.exceptions.NoSuchEntityException;

import java.util.List;

public interface CommentService {

    List<CommentDto> getComments(CommentTargetType type, Long targetId);

    CommentDto createComment(CommentTargetType type, Long targetId, CommentCreateRequest request);

    CommentDto updateComment(Long commentId, CommentUpdateRequest request) throws NoSuchEntityException;
    void deleteComment(Long commentId) throws NoSuchEntityException;
}
