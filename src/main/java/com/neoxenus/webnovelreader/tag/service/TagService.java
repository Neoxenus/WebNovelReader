package com.neoxenus.webnovelreader.tag.service;

import com.neoxenus.webnovelreader.tag.dto.TagDto;
import com.neoxenus.webnovelreader.tag.enums.TagType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagService {

    List<TagDto> getTagsByType(TagType type);

    TagDto getById(Long id);
}
