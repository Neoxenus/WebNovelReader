package com.neoxenus.webnovelreader.comment.repo;

import com.neoxenus.webnovelreader.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBookIdAndParentIsNull(Long bookId);
    List<Comment> findAllByChapterIdAndParentIsNull(Long chapterId);
    List<Comment> findAllByUserId(Long bookId);
}
