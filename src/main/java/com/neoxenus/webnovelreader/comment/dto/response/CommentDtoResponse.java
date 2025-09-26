package com.neoxenus.webnovelreader.comment.dto.response;

import com.neoxenus.webnovelreader.user.dto.response.UserDtoResponse;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record CommentDtoResponse(
        Long id,
        UserDtoResponse user,
        Long bookId,
        Long chapterId,
        String content,
        Long parentId,
        List<CommentDtoResponse> replies,
        LocalDateTime createdAt,
        Long likesCount,
        Long dislikesCount
) {
}
