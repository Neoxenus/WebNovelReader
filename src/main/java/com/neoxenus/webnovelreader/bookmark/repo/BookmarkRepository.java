package com.neoxenus.webnovelreader.bookmark.repo;

import com.neoxenus.webnovelreader.bookmark.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findBookmarksByCollectionsId(Long id);
    boolean existsByBookIdAndChapterNullAndCollectionsUserId(Long bookId, Long userId);
    boolean existsByBookIdAndChapterIdAndCollectionsUserId(Long bookId, Long chapterId, Long userId);

}
