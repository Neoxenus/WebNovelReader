package com.neoxenus.webnovelreader.comments.mapper.impl;

import com.neoxenus.webnovelreader.books.entities.Book;
import com.neoxenus.webnovelreader.chapters.etitities.Chapter;
import com.neoxenus.webnovelreader.comments.entities.Comment;
import com.neoxenus.webnovelreader.comments.entities.dtos.CommentCreateRequest;
import com.neoxenus.webnovelreader.comments.entities.dtos.CommentDto;
import com.neoxenus.webnovelreader.comments.entities.dtos.CommentUpdateRequest;
import com.neoxenus.webnovelreader.comments.mapper.CommentMapper;
import com.neoxenus.webnovelreader.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

    private  <T, R> R getNullable(T obj, Function<T, R> getter) {
        return obj != null ? getter.apply(obj) : null;
    }

    @Override
    public Optional<CommentDto> toDto(Optional<Comment> comment) {
        return Optional.ofNullable(toDto(comment.orElse(null)));
    }

    @Override
    public List<CommentDto> toDto(List<Comment> comment) {
        return comment.stream().map(this::toDto).toList();
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
