package com.neoxenus.webnovelreader.comment.mapper.impl;

import com.neoxenus.webnovelreader.book.entity.Book;
import com.neoxenus.webnovelreader.chapter.etitity.Chapter;
import com.neoxenus.webnovelreader.comment.dto.response.CommentDtoResponse;
import com.neoxenus.webnovelreader.comment.dto.request.CommentCreateDtoRequest;
import com.neoxenus.webnovelreader.comment.dto.request.CommentUpdateDtoRequest;
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
    public CommentDtoResponse toDto(Comment comment) {
        if(Objects.isNull(comment))
            return null;
        return CommentDtoResponse.builder()
                .id(comment.getId())
                .user(userMapper.toDto(comment.getUser()))
                .bookId(getNullable(comment.getBook(), Book::getId))
                .chapterId(getNullable(comment.getChapter(), Chapter::getId))
                .content(comment.getContent())
                .parentId(getNullable(comment.getParent(), Comment::getId))
                .replies(toDto(comment.getReplies()))
                .createdAt(comment.getCreatedAt())
                .likesCount(comment.getLikesCount())
                .dislikesCount(comment.getDislikesCount())
                .build();
    }

    @Override
    public CommentDtoResponse toDtoWithoutReplies(Comment comment) {
        if(Objects.isNull(comment))
            return null;
        return CommentDtoResponse.builder()
                .id(comment.getId())
                .user(userMapper.toDto(comment.getUser()))
                .bookId(getNullable(comment.getBook(), Book::getId))
                .chapterId(getNullable(comment.getChapter(), Chapter::getId))
                .content(comment.getContent())
                .parentId(getNullable(comment.getParent(), Comment::getId))
                .createdAt(comment.getCreatedAt())
                .likesCount(comment.getLikesCount())
                .dislikesCount(comment.getDislikesCount())
                .build();
    }

    private  <T, R> R getNullable(T obj, Function<T, R> getter) {
        return obj != null ? getter.apply(obj) : null;
    }

    @Override
    public List<CommentDtoResponse> toDto(List<Comment> comment) {
        return comment.stream().map(this::toDto).toList();
    }

    @Override
    public List<CommentDtoResponse> toDtoWithoutReplies(List<Comment> comment) {
        return comment.stream().map(this::toDtoWithoutReplies).toList();
    }

    @Override
    public Comment toComment(CommentCreateDtoRequest createRequest) {
        if(createRequest != null){
            Comment comment = new Comment();
            comment.setContent(createRequest.content());
            comment.setLikesCount(0L);
            comment.setDislikesCount(0L);
            return comment;
        } else {
            return null;
        }
    }

    @Override
    public Comment toComment(Comment toUpdate, CommentUpdateDtoRequest updateRequest) {
        if(toUpdate != null && updateRequest != null) {
            toUpdate.setContent(updateRequest.content());
            return toUpdate;
        } else {
            return null;
        }
    }
}
