package com.neoxenus.webnovelreader.comment.mapper;

import com.neoxenus.webnovelreader.comment.entity.Comment;
import com.neoxenus.webnovelreader.comment.dto.request.CommentCreateDtoRequest;
import com.neoxenus.webnovelreader.comment.dto.response.CommentDtoResponse;
import com.neoxenus.webnovelreader.comment.dto.request.CommentUpdateDtoRequest;

import java.util.List;

public interface CommentMapper {

    CommentDtoResponse toDto(Comment comment);
    CommentDtoResponse toDtoWithoutReplies(Comment comment);
    List<CommentDtoResponse> toDto(List<Comment> comment);
    List<CommentDtoResponse> toDtoWithoutReplies(List<Comment> comment);


    Comment toComment(CommentCreateDtoRequest createRequest);

    Comment toComment(Comment toUpdate, CommentUpdateDtoRequest updateRequest);
}
