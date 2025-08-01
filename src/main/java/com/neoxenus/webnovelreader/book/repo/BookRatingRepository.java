package com.neoxenus.webnovelreader.book.repo;

import com.neoxenus.webnovelreader.book.dto.projection.BookRatingAverages;
import com.neoxenus.webnovelreader.book.entity.BookRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRatingRepository extends JpaRepository<BookRating, Long> {
    @Query("SELECT " +
            "COUNT(br), " +
            "AVG(br.storyDevelopment) as avgStoryDevelopment, " +
            "AVG(br.writingQuality) as avgWritingQuality, " +
            "AVG(br.worldBackground) as avgWorldBackground, " +
            "AVG(br.characterDesign) as avgCharacterDesign " +
            "FROM BookRating br WHERE br.book.id = :bookId")
    BookRatingAverages getAveragesByBookId(@Param("bookId") Long bookId);
}
