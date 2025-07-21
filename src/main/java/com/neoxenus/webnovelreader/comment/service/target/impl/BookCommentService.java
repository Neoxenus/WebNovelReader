package com.neoxenus.webnovelreader.comment.service.target.impl;

import com.neoxenus.webnovelreader.book.entity.Book;
import com.neoxenus.webnovelreader.book.repo.BookRepository;
import com.neoxenus.webnovelreader.comment.entity.Comment;
import com.neoxenus.webnovelreader.comment.dto.request.CommentCreateRequest;
import com.neoxenus.webnovelreader.comment.dto.CommentDto;
import com.neoxenus.webnovelreader.comment.enums.CommentTargetType;
import com.neoxenus.webnovelreader.comment.mapper.CommentMapper;
import com.neoxenus.webnovelreader.comment.repo.CommentRepository;
import com.neoxenus.webnovelreader.comment.service.target.CommentTargetService;
import com.neoxenus.webnovelreader.exceptions.NoSuchEntityException;
import com.neoxenus.webnovelreader.exceptions.UnexpectedUnauthenticatedUserException;
import com.neoxenus.webnovelreader.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookCommentService implements CommentTargetService {

    private final CommentMapper commentMapper;
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    private final UserService userService;

    @Override
    public CommentTargetType getTargetType() {
        return CommentTargetType.BOOK;
    }

    @Override
    public List<CommentDto> getComments(Long targetId) {
        List<Comment> comments = commentRepository.findAllByBookIdAndParentIsNull(targetId);
        return commentMapper.toDto(comments);
    }

    @Override
    public CommentDto createComment(Long targetId, CommentCreateRequest request) {

        Comment comment = commentMapper.toComment(request);
        Book book = bookRepository
                .findById(targetId)
                .orElseThrow(() -> new NoSuchEntityException("No such book for id: " + targetId));

        Comment parent = Optional.ofNullable(request.getParentId())
                        .map(id -> commentRepository.findById(id)
                                .orElseThrow(() -> new NoSuchEntityException("No such parent comment for id: " + id)))
                        .orElse(null);
        comment.setParent(parent);
        comment.setBook(book);
        Optional.ofNullable(userService.getCurrentUser())
                .orElseThrow(() -> new UnexpectedUnauthenticatedUserException("User is not allowed to comment without authorization."));
        comment.setUser(userService.getCurrentUser());
        return commentMapper.toDto(
                commentRepository.save(comment)
        );
    }
}
