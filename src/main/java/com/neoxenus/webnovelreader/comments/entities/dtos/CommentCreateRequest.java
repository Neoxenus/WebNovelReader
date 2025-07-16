package com.neoxenus.webnovelreader.comments.entities.dtos;

import lombok.Data;

@Data
public class CommentCreateRequest {

        private Long userId;

        private Long bookId;
        private Long chapterId;

        private String content;

        private Long parentId;
}
