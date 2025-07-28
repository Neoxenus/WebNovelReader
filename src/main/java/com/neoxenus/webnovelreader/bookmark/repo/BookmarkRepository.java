package com.neoxenus.webnovelreader.bookmark.repo;

import com.neoxenus.webnovelreader.bookmark.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findBookmarksByCollectionId(Long id);

//    Bookmark findBookmarksByBookIdAndChapterId(Long bookId, Long chapterId);
//    Bookmark findBookmarksByBookIdAndChapterNull(Long bookId);


    void deleteAllByCollectionId(Long id);
}
