package com.neoxenus.webnovelreader.chapter.repo;

import com.neoxenus.webnovelreader.chapter.etitity.Chapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    Page<Chapter> findAllByBookId(Long bookId, Pageable pageable);
    @Modifying
    @Query("UPDATE Chapter c SET c.views = c.views + :count WHERE c.id = :id")
    void incrementViewCount(@Param("id") Long id, @Param("count") Long count);

    boolean existsByBookIdAndChapterNumber(Long id, Integer number);


}
