package com.neoxenus.webnovelreader.comment.dto.request;

import lombok.Data;

@Data
public class CommentCreateRequest {

        private String content;

        private Long parentId;
}
