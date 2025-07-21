package com.neoxenus.webnovelreader.comment.dto;

import com.neoxenus.webnovelreader.user.dto.UserDto;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record CommentDto(
        Long id,
        UserDto user,
        Long bookId,
        Long chapterId,
        String content,
        Long parentId,
        List<CommentDto> replies,
        LocalDateTime createdAt) {
}
