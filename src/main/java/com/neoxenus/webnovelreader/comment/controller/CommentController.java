package com.neoxenus.webnovelreader.comment.controller;

import com.neoxenus.webnovelreader.comment.dto.response.CommentDtoResponse;
import com.neoxenus.webnovelreader.comment.dto.request.CommentCreateDtoRequest;
import com.neoxenus.webnovelreader.comment.dto.request.CommentUpdateDtoRequest;
import com.neoxenus.webnovelreader.comment.dto.request.VoteDtoRequest;
import com.neoxenus.webnovelreader.comment.enums.CommentTargetType;
import com.neoxenus.webnovelreader.comment.service.CommentService;
import com.neoxenus.webnovelreader.comment.service.CommentVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class CommentController {
    private final CommentService commentService;
    private final CommentVoteService voteService;
    @PostMapping("/{targetType}/{targetId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDtoResponse postComment(@PathVariable CommentTargetType targetType,
                                          @PathVariable Long targetId,
                                          @RequestBody CommentCreateDtoRequest request) {

        return commentService.createComment(targetType, targetId, request);
    }
    @GetMapping("/{targetType}/{targetId}/comments")
    public List<CommentDtoResponse> getComments(@PathVariable CommentTargetType targetType,
                                                @PathVariable Long targetId) {
        return commentService.getComments(targetType, targetId);
    }

    @PatchMapping("/comments/{id}")
    public CommentDtoResponse editComment(@PathVariable Long id,
                                          @RequestBody CommentUpdateDtoRequest request) {
        return commentService.updateComment(id, request);
    }

    @DeleteMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }

    @PostMapping("/comments/{id}/vote")
    public CommentDtoResponse voteOnComment(@PathVariable Long id,
                                            @RequestBody VoteDtoRequest vote) {
        return voteService.vote(id, vote);
    }

}
