package com.neoxenus.webnovelreader.comments.mapper;

import com.neoxenus.webnovelreader.comments.entities.Comment;
import com.neoxenus.webnovelreader.comments.entities.dtos.CommentCreateRequest;
import com.neoxenus.webnovelreader.comments.entities.dtos.CommentDto;
import com.neoxenus.webnovelreader.comments.entities.dtos.CommentUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface CommentMapper {

    CommentDto toDto(Comment comment);
    Optional<CommentDto> toDto(Optional<Comment> comment);
    List<CommentDto> toDto(List<Comment> comment);

    Comment toComment(CommentCreateRequest createRequest);

    Comment toComment(Comment toUpdate, CommentUpdateRequest updateRequest);
}
