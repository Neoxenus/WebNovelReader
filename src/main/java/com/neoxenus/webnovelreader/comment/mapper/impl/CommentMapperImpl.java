package com.neoxenus.webnovelreader.comment.mapper.impl;

import com.neoxenus.webnovelreader.book.entity.Book;
import com.neoxenus.webnovelreader.chapter.etitity.Chapter;
import com.neoxenus.webnovelreader.comment.dto.CommentDto;
import com.neoxenus.webnovelreader.comment.dto.request.CommentCreateRequest;
import com.neoxenus.webnovelreader.comment.dto.request.CommentUpdateRequest;
import com.neoxenus.webnovelreader.comment.entity.Comment;
import com.neoxenus.webnovelreader.comment.mapper.CommentMapper;
import com.neoxenus.webnovelreader.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class CommentMapperImpl implements CommentMapper {
    private final UserMapper userMapper;
    @Override
    public CommentDto toDto(Comment comment) {
        if(Objects.isNull(comment))
            return null;
        return CommentDto.builder()
                .id(comment.getId())
                .user(userMapper.toDto(comment.getUser()))
                .bookId(getNullable(comment.getBook(), Book::getId))
                .chapterId(getNullable(comment.getChapter(), Chapter::getId))
                .content(comment.getContent())
                .parentId(getNullable(comment.getParent(), Comment::getId))
                .replies(toDto(comment.getReplies()))
                .createdAt(comment.getCreatedAt())
                .build();
    }

    @Override
    public CommentDto toDtoWithoutReplies(Comment comment) {
        if(Objects.isNull(comment))
            return null;
        return CommentDto.builder()
                .id(comment.getId())
                .user(userMapper.toDto(comment.getUser()))
                .bookId(getNullable(comment.getBook(), Book::getId))
                .chapterId(getNullable(comment.getChapter(), Chapter::getId))
                .content(comment.getContent())
                .parentId(getNullable(comment.getParent(), Comment::getId))
                .createdAt(comment.getCreatedAt())
                .build();
    }

    private  <T, R> R getNullable(T obj, Function<T, R> getter) {
        return obj != null ? getter.apply(obj) : null;
    }

    @Override
    public List<CommentDto> toDto(List<Comment> comment) {
        return comment.stream().map(this::toDto).toList();
    }

    @Override
    public List<CommentDto> toDtoWithoutReplies(List<Comment> comment) {
        return comment.stream().map(this::toDtoWithoutReplies).toList();
    }

    @Override
    public Comment toComment(CommentCreateRequest createRequest) {
        if(createRequest != null){
            Comment comment = new Comment();
            comment.setContent(createRequest.getContent());
            return comment;
        } else {
            return null;
        }
    }

    @Override
    public Comment toComment(Comment toUpdate, CommentUpdateRequest updateRequest) {
        if(toUpdate != null && updateRequest != null) {
            toUpdate.setContent(updateRequest.getContent());
            return toUpdate;
        } else {
            return null;
        }
    }
}
