package com.neoxenus.webnovelreader.bookmarkcollection.repo;

import com.neoxenus.webnovelreader.bookmark.repo.BookmarkRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkCollectionRepository extends JpaRepository<BookmarkRepository, Long> {
}
