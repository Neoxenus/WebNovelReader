package com.neoxenus.webnovelreader.book.repo;

import com.neoxenus.webnovelreader.book.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
