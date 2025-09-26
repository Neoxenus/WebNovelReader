package com.neoxenus.webnovelreader.tag.mapper.impl;

import com.neoxenus.webnovelreader.tag.dto.response.TagDtoResponse;
import com.neoxenus.webnovelreader.tag.dto.response.TagSummaryDtoResponse;
import com.neoxenus.webnovelreader.tag.dto.request.TagDtoRequest;
import com.neoxenus.webnovelreader.tag.entity.Tag;
import com.neoxenus.webnovelreader.tag.mapper.TagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TagMapperImpl implements TagMapper {

    @Override
    public TagDtoResponse toDto(Tag tag) {
        return TagDtoResponse.builder()
                .id(tag.getId())
                .name(tag.getName())
                .description(tag.getDescription())
                .tagType(tag.getTagType())
                .build();
    }

    @Override
    public List<TagDtoResponse> toDto(List<Tag> tags) {
        return tags.stream().map(this::toDto).toList();
    }

    @Override
    public TagSummaryDtoResponse toSummaryDto(Tag tag) {
        return TagSummaryDtoResponse.builder()
                .id(tag.getId())
                .name(tag.getName())
                .tagType(tag.getTagType())
                .build();
    }

    @Override
    public List<TagSummaryDtoResponse> toSummaryDto(List<Tag> tags) {
        return tags.stream().map(this::toSummaryDto).toList();
    }

    @Override
    public Tag toTag(TagDtoRequest request, Tag tag) {
        if(tag == null) {
            return Tag.builder()
                    .tagType(request.tagType())
                    .name(request.name())
                    .description(request.description())
                    .build();

        } else {
            tag.setTagType(request.tagType());
            tag.setName(request.name());
            tag.setDescription(request.description());
            return tag;
        }
    }
}
