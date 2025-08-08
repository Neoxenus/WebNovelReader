package com.neoxenus.webnovelreader.book.mapper.impl;

import com.neoxenus.webnovelreader.book.dto.TagDto;
import com.neoxenus.webnovelreader.book.entity.Tag;
import com.neoxenus.webnovelreader.book.mapper.TagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TagMapperImpl implements TagMapper {

    @Override
    public TagDto toDto(Tag tag) {
        return TagDto.builder()
                .id(tag.getId())
                .name(tag.getName())
                .description(tag.getDescription())
                .tagType(tag.getTagType())
                .build();
    }

    @Override
    public List<TagDto> toDto(List<Tag> tags) {
        return tags.stream().map(this::toDto).toList();
    }
}
