package com.neoxenus.webnovelreader.comment.dto.request;

public record CommentCreateRequest(
        String content,
        Long parentId) {

}
