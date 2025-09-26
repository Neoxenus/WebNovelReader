package com.neoxenus.webnovelreader.tag.mapper;

import com.neoxenus.webnovelreader.tag.dto.response.TagDtoResponse;
import com.neoxenus.webnovelreader.tag.dto.response.TagSummaryDtoResponse;
import com.neoxenus.webnovelreader.tag.dto.request.TagDtoRequest;
import com.neoxenus.webnovelreader.tag.entity.Tag;

import java.util.List;

public interface TagMapper {
    TagDtoResponse toDto(Tag tag);
    List<TagDtoResponse> toDto(List<Tag> tag);

    TagSummaryDtoResponse toSummaryDto(Tag tag);
    List<TagSummaryDtoResponse> toSummaryDto(List<Tag> tag);

    Tag toTag(TagDtoRequest request, Tag tag);
}
