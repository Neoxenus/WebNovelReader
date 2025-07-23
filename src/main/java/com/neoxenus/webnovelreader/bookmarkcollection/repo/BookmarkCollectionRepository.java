package com.neoxenus.webnovelreader.bookmarkcollection.repo;

import com.neoxenus.webnovelreader.bookmarkcollection.dto.projection.BookmarkCountProjection;
import com.neoxenus.webnovelreader.bookmarkcollection.entity.BookmarkCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookmarkCollectionRepository extends JpaRepository<BookmarkCollection, Long> {
    List<BookmarkCollection> findByUserId(Long userId);

    @Query("""
    SELECT c.id AS id, COUNT(b.id) AS count
    FROM BookmarkCollection c
    LEFT JOIN c.bookmarks b
    WHERE c.user.id = :userId
    GROUP BY c.id
""")
    List<BookmarkCountProjection> findBookmarkCountsByUserId(@Param("userId") Long userId);

    @Query("SELECT MAX(c.position) FROM BookmarkCollection c WHERE c.user.id = :userId")
    Optional<Integer> findMaxPositionByUserId(@Param("userId") Long userId);
}
