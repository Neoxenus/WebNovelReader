package com.neoxenus.webnovelreader.comment.service.impl;

import com.neoxenus.webnovelreader.comment.dto.CommentDto;
import com.neoxenus.webnovelreader.comment.dto.request.VoteRequest;
import com.neoxenus.webnovelreader.comment.entity.Comment;
import com.neoxenus.webnovelreader.comment.entity.CommentVote;
import com.neoxenus.webnovelreader.comment.enums.VoteType;
import com.neoxenus.webnovelreader.comment.mapper.CommentMapper;
import com.neoxenus.webnovelreader.comment.repo.CommentRepository;
import com.neoxenus.webnovelreader.comment.repo.CommentVoteRepository;
import com.neoxenus.webnovelreader.comment.service.CommentVoteService;
import com.neoxenus.webnovelreader.exceptions.NoSuchEntityException;
import com.neoxenus.webnovelreader.user.entity.User;
import com.neoxenus.webnovelreader.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentVoteServiceImpl implements CommentVoteService {
    private final CommentVoteRepository voteRepo;
    private final CommentRepository commentRepo;

    private final CommentMapper mapper;

    private final UserService userService;
    @Override
    @Transactional
    public CommentDto vote(Long id, VoteRequest request) {
        User currentUser = userService.getCurrentUser();
        Comment comment = commentRepo.findById(id).orElseThrow(
                () -> new NoSuchEntityException("No comments for an id: " + id)
        );
        Optional<CommentVote> existingVote = voteRepo.findByUserIdAndCommentId(currentUser.getId(), id);

        if (existingVote.isPresent()) {
            CommentVote voteEntity = existingVote.get();
            if(!request.vote().equals(voteEntity.getVote())){
                voteEntity.setVote(request.vote());
                voteRepo.save(voteEntity);
            }

        } else {
            CommentVote newVote = new CommentVote();
            newVote.setComment(commentRepo.getReferenceById(id));
            newVote.setUser(currentUser);
            newVote.setVote(request.vote());
            voteRepo.save(newVote);
        }

        long likes = voteRepo.countByCommentIdAndVote(id, VoteType.LIKE);
        long dislikes = voteRepo.countByCommentIdAndVote(id, VoteType.DISLIKE);
        comment.setLikesCount(likes);
        comment.setDislikesCount(dislikes);
        commentRepo.save(comment);

        return mapper.toDto(comment);
    }
}
