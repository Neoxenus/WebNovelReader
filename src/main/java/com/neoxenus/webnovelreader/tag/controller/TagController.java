package com.neoxenus.webnovelreader.tag.controller;


import com.neoxenus.webnovelreader.tag.dto.TagDto;
import com.neoxenus.webnovelreader.tag.enums.TagType;
import com.neoxenus.webnovelreader.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tags")
@Slf4j
public class TagController {

    private final TagService service;

    @GetMapping("/type/{type}")
    public List<TagDto> getTagsByType(@PathVariable TagType type) {
        return service.getTagsByType(type);
    }

    @GetMapping("/{id}")
    public TagDto getTagsById(@PathVariable Long id) {
        return service.getById(id);
    }
}
