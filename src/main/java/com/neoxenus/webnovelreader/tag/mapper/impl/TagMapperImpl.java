package com.neoxenus.webnovelreader.tag.mapper.impl;

import com.neoxenus.webnovelreader.tag.dto.TagDto;
import com.neoxenus.webnovelreader.tag.dto.TagSummaryDto;
import com.neoxenus.webnovelreader.tag.entity.Tag;
import com.neoxenus.webnovelreader.tag.mapper.TagMapper;
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

    @Override
    public TagSummaryDto toSummaryDto(Tag tag) {
        return TagSummaryDto.builder()
                .id(tag.getId())
                .name(tag.getName())
                .tagType(tag.getTagType())
                .build();
    }

    @Override
    public List<TagSummaryDto> toSummaryDto(List<Tag> tags) {
        return tags.stream().map(this::toSummaryDto).toList();
    }
}
