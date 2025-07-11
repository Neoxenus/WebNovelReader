package com.neoxenus.webnovelreader.chapters.repo;

import com.neoxenus.webnovelreader.chapters.etitities.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
}
