package com.neoxenus.webnovelreader.comment.service;

import com.neoxenus.webnovelreader.comment.dto.request.CommentCreateDtoRequest;
import com.neoxenus.webnovelreader.comment.dto.response.CommentDtoResponse;
import com.neoxenus.webnovelreader.comment.dto.request.CommentUpdateDtoRequest;
import com.neoxenus.webnovelreader.comment.enums.CommentTargetType;
import com.neoxenus.webnovelreader.exceptions.NoSuchEntityException;

import java.util.List;

public interface CommentService {

    List<CommentDtoResponse> getComments(CommentTargetType type, Long targetId);

    CommentDtoResponse createComment(CommentTargetType type, Long targetId, CommentCreateDtoRequest request);

    CommentDtoResponse updateComment(Long commentId, CommentUpdateDtoRequest request) throws NoSuchEntityException;
    void deleteComment(Long commentId) throws NoSuchEntityException;
}
