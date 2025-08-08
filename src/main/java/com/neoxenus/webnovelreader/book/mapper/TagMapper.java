package com.neoxenus.webnovelreader.book.mapper;

import com.neoxenus.webnovelreader.book.dto.TagDto;
import com.neoxenus.webnovelreader.book.entity.Tag;

import java.util.List;

public interface TagMapper {
    TagDto toDto(Tag tag);
    List<TagDto> toDto(List<Tag> tag);
}
