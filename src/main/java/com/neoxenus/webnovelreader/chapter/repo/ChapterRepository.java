package com.neoxenus.webnovelreader.chapter.repo;

import com.neoxenus.webnovelreader.chapter.etitity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    List<Chapter> findByBookId(Long bookId);
    @Modifying
    @Query("UPDATE Chapter c SET c.views = c.views + 1 WHERE c.id = :id")
    void incrementViewCount(@Param("id") Long id);

    boolean existsByBookIdAndChapterNumber(Long id, Integer number);


}
