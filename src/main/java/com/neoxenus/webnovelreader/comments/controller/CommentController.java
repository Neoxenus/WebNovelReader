package com.neoxenus.webnovelreader.comments.controller;

import com.neoxenus.webnovelreader.comments.entities.dtos.CommentDto;
import com.neoxenus.webnovelreader.comments.enums.CommentTargetType;
import com.neoxenus.webnovelreader.comments.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/{targetType}/{targetId}/comments")
public class CommentController {
    private final CommentService commentService;
    @GetMapping
    public ResponseEntity<?> getComments(@PathVariable CommentTargetType targetType,
                                         @PathVariable Long targetId) {
        List<CommentDto> comments = commentService.getComments(targetType, targetId);
        return ResponseEntity.ok().body(comments);
    }
}
