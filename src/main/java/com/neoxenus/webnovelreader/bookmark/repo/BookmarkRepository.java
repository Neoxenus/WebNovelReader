package com.neoxenus.webnovelreader.bookmark.repo;

import com.neoxenus.webnovelreader.bookmark.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
}
