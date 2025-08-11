package com.neoxenus.webnovelreader.tag.service.impl;

import com.neoxenus.webnovelreader.exceptions.NoSuchEntityException;
import com.neoxenus.webnovelreader.tag.dto.TagDto;
import com.neoxenus.webnovelreader.tag.enums.TagType;
import com.neoxenus.webnovelreader.tag.mapper.TagMapper;
import com.neoxenus.webnovelreader.tag.repo.TagRepository;
import com.neoxenus.webnovelreader.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TagServiceImpl implements TagService {

    private final TagRepository repository;

    private final TagMapper mapper;

    @Override
    public List<TagDto> getTagsByType(TagType type) {
        return mapper.toDto(repository.findTagByTagType(type));
    }

    @Override
    public TagDto getById(Long id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException("No tag with an id: " + id)));
    }
}
