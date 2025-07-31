package com.neoxenus.webnovelreader.comment.repo;

import com.neoxenus.webnovelreader.comment.entity.CommentVote;
import com.neoxenus.webnovelreader.comment.enums.VoteType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentVoteRepository extends JpaRepository<CommentVote, Long> {
    long countByCommentIdAndVote(Long commentId, VoteType vote);

    Optional<CommentVote> findByUserIdAndCommentId(Long userId, Long commentId);
}
