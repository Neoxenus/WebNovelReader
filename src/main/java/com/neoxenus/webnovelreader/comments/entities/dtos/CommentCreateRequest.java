package com.neoxenus.webnovelreader.comments.entities.dtos;

import lombok.Data;

@Data
public class CommentCreateRequest {

        private String content;

        private Long parentId;
}
