package com.neoxenus.webnovelreader.comments.entities.dtos;

import com.neoxenus.webnovelreader.users.entities.dtos.UserDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class CommentDto {
    private Long id;

    private UserDto user;

    private Long bookId;

    private Long chapterId;

    private String content;

    private Long parentId;

    private List<CommentDto> replies;

    private LocalDateTime createdAt;
}
