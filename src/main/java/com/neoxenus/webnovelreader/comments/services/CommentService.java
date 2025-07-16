package com.neoxenus.webnovelreader.comments.services;

import com.neoxenus.webnovelreader.comments.entities.dtos.CommentCreateRequest;
import com.neoxenus.webnovelreader.comments.entities.dtos.CommentDto;
import com.neoxenus.webnovelreader.comments.entities.dtos.CommentUpdateRequest;
import com.neoxenus.webnovelreader.comments.enums.CommentTargetType;

import java.util.List;

public interface CommentService {

    List<CommentDto> getComments(CommentTargetType type, Long targetId);

    CommentDto createComment(CommentTargetType type, Long targetId, CommentCreateRequest request);

    CommentDto updateComment(Long commentId, CommentUpdateRequest request);
    void deleteComment(Long commentId);
}
