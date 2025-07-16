package com.neoxenus.webnovelreader.comments.mapper;

import com.neoxenus.webnovelreader.comments.entities.Comment;
import com.neoxenus.webnovelreader.comments.entities.dtos.CommentCreateRequest;
import com.neoxenus.webnovelreader.comments.entities.dtos.CommentDto;
import com.neoxenus.webnovelreader.comments.entities.dtos.CommentUpdateRequest;

import java.util.List;

public interface CommentMapper {

    CommentDto toDto(Comment comment);

    List<CommentDto> toDto(List<Comment> comment);

    Comment toComment(CommentCreateRequest createRequest);

    Comment toComment(Comment toUpdate, CommentUpdateRequest updateRequest);
}
