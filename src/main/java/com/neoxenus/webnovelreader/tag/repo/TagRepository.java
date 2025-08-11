package com.neoxenus.webnovelreader.tag.repo;

import com.neoxenus.webnovelreader.tag.entity.Tag;
import com.neoxenus.webnovelreader.tag.enums.TagType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findTagByTagType(TagType type);
}
