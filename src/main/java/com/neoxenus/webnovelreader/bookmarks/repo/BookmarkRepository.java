package com.neoxenus.webnovelreader.bookmarks.repo;

import com.neoxenus.webnovelreader.bookmarks.entities.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
}
