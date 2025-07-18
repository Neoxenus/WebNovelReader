package com.neoxenus.webnovelreader.comments.controller;

import com.neoxenus.webnovelreader.comments.entities.dtos.CommentCreateRequest;
import com.neoxenus.webnovelreader.comments.entities.dtos.CommentDto;
import com.neoxenus.webnovelreader.comments.entities.dtos.CommentUpdateRequest;
import com.neoxenus.webnovelreader.comments.enums.CommentTargetType;
import com.neoxenus.webnovelreader.comments.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/{targetType}/{targetId}/comments")
    public ResponseEntity<?> postComment(@PathVariable CommentTargetType targetType,
                                         @PathVariable Long targetId,
                                         @RequestBody CommentCreateRequest request) {
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/" + targetType + "/" + targetId + "/comments").toUriString());
        return ResponseEntity.created(uri).body(commentService.createComment(targetType, targetId, request));
    }
    @GetMapping("/{targetType}/{targetId}/comments")
    public ResponseEntity<?> getComments(@PathVariable CommentTargetType targetType,
                                         @PathVariable Long targetId) {
        List<CommentDto> comments = commentService.getComments(targetType, targetId);
        return ResponseEntity.ok().body(comments);
    }

    @PatchMapping("/comments/{id}")
    public ResponseEntity<?> editComment(@PathVariable Long id,
                                         @RequestBody CommentUpdateRequest request) {
        CommentDto comment = commentService.updateComment(id, request);
        return ResponseEntity.ok().body(comment);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

}
