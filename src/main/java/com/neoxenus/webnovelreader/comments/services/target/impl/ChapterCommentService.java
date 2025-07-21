package com.neoxenus.webnovelreader.comments.services.target.impl;

import com.neoxenus.webnovelreader.chapters.etitities.Chapter;
import com.neoxenus.webnovelreader.chapters.repo.ChapterRepository;
import com.neoxenus.webnovelreader.comments.entities.Comment;
import com.neoxenus.webnovelreader.comments.entities.dtos.CommentCreateRequest;
import com.neoxenus.webnovelreader.comments.entities.dtos.CommentDto;
import com.neoxenus.webnovelreader.comments.enums.CommentTargetType;
import com.neoxenus.webnovelreader.comments.mapper.CommentMapper;
import com.neoxenus.webnovelreader.comments.repo.CommentRepository;
import com.neoxenus.webnovelreader.comments.services.target.CommentTargetService;
import com.neoxenus.webnovelreader.exceptions.NoSuchEntityException;
import com.neoxenus.webnovelreader.exceptions.UnexpectedUnauthenticatedUserException;
import com.neoxenus.webnovelreader.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChapterCommentService implements CommentTargetService {
    private final CommentMapper commentMapper;
    private final ChapterRepository chapterRepository;
    private final CommentRepository commentRepository;

    private final UserService userService;

    @Override
    public CommentTargetType getTargetType() {
        return CommentTargetType.CHAPTER;
    }

    @Override
    public List<CommentDto> getComments(Long targetId) {
        List<Comment> comments = commentRepository.findAllByChapterIdAndParentIsNull(targetId);
        return commentMapper.toDto(comments);
    }

    @Override
    public CommentDto createComment(Long targetId, CommentCreateRequest request) {

        Comment comment = commentMapper.toComment(request);
        Chapter chapter = chapterRepository
                .findById(targetId)
                .orElseThrow(() -> new NoSuchEntityException("No such chapter for id: " + targetId));

        Comment parent = Optional.ofNullable(request.getParentId())
                .map(id -> commentRepository.findById(id)
                        .orElseThrow(() -> new NoSuchEntityException("No such parent comment for id: " + id)))
                .orElse(null);
        comment.setParent(parent);
        comment.setChapter(chapter);
        Optional.ofNullable(userService.getCurrentUser())
                .orElseThrow(() -> new UnexpectedUnauthenticatedUserException("User is not allowed to comment without authorization."));
        comment.setUser(userService.getCurrentUser());
        return commentMapper.toDto(
                commentRepository.save(comment)
        );
    }
}
