package com.neoxenus.webnovelreader.tag.mapper;

import com.neoxenus.webnovelreader.tag.dto.TagDto;
import com.neoxenus.webnovelreader.tag.entity.Tag;

import java.util.List;

public interface TagMapper {
    TagDto toDto(Tag tag);
    List<TagDto> toDto(List<Tag> tag);
}
