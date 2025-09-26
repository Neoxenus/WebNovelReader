package com.neoxenus.webnovelreader.tag.service;

import com.neoxenus.webnovelreader.tag.dto.response.TagDtoResponse;
import com.neoxenus.webnovelreader.tag.dto.request.TagDtoRequest;
import com.neoxenus.webnovelreader.tag.enums.TagType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagService {

    List<TagDtoResponse> getTagsByType(TagType type);

    TagDtoResponse getById(Long id);

    TagDtoResponse saveTag(TagDtoRequest request);

    TagDtoResponse updateTag(Long id, TagDtoRequest request);

    void delete(Long id, boolean isForceDelete);
}
