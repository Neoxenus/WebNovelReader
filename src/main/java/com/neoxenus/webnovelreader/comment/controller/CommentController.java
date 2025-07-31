package com.neoxenus.webnovelreader.comment.controller;

import com.neoxenus.webnovelreader.comment.dto.CommentDto;
import com.neoxenus.webnovelreader.comment.dto.request.CommentCreateRequest;
import com.neoxenus.webnovelreader.comment.dto.request.CommentUpdateRequest;
import com.neoxenus.webnovelreader.comment.dto.request.VoteRequest;
import com.neoxenus.webnovelreader.comment.enums.CommentTargetType;
import com.neoxenus.webnovelreader.comment.service.CommentService;
import com.neoxenus.webnovelreader.comment.service.CommentVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;
    private final CommentVoteService voteService;
    @PostMapping("/{targetType}/{targetId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto postComment(       @PathVariable CommentTargetType targetType,
                                         @PathVariable Long targetId,
                                         @RequestBody CommentCreateRequest request) {

        return commentService.createComment(targetType, targetId, request);
    }
    @GetMapping("/{targetType}/{targetId}/comments")
    public List<CommentDto> getComments( @PathVariable CommentTargetType targetType,
                                         @PathVariable Long targetId) {
        return commentService.getComments(targetType, targetId);
    }

    @PatchMapping("/comments/{id}")
    public CommentDto editComment(  @PathVariable Long id,
                                    @RequestBody CommentUpdateRequest request) {
        return commentService.updateComment(id, request);
    }

    @DeleteMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }

    @PostMapping("/comments/{id}/vote")
    public CommentDto voteOnComment(    @PathVariable Long id,
                                        @RequestBody VoteRequest vote) {
        return voteService.vote(id, vote);
    }

}
