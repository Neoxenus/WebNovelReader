package com.neoxenus.webnovelreader.comment.dto.request;

public record CommentCreateDtoRequest(
        String content,
        Long parentId) {

}
