package com.neoxenus.webnovelreader.comment.mapper;

import com.neoxenus.webnovelreader.comment.entity.Comment;
import com.neoxenus.webnovelreader.comment.dto.request.CommentCreateRequest;
import com.neoxenus.webnovelreader.comment.dto.CommentDto;
import com.neoxenus.webnovelreader.comment.dto.request.CommentUpdateRequest;

import java.util.List;

public interface CommentMapper {

    CommentDto toDto(Comment comment);
    CommentDto toDtoWithoutReplies(Comment comment);
    List<CommentDto> toDto(List<Comment> comment);
    List<CommentDto> toDtoWithoutReplies(List<Comment> comment);


    Comment toComment(CommentCreateRequest createRequest);

    Comment toComment(Comment toUpdate, CommentUpdateRequest updateRequest);
}
