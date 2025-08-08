package com.neoxenus.webnovelreader.tag.repo;

import com.neoxenus.webnovelreader.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
