package com.neoxenus.webnovelreader.bookmark.repo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkCollectionRepository extends JpaRepository<BookmarkRepository, Long> {
}
