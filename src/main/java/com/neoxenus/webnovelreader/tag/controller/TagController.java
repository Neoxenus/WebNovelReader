package com.neoxenus.webnovelreader.tag.controller;


import com.neoxenus.webnovelreader.tag.dto.TagDto;
import com.neoxenus.webnovelreader.tag.dto.request.TagRequest;
import com.neoxenus.webnovelreader.tag.enums.TagType;
import com.neoxenus.webnovelreader.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tags")
@Slf4j
public class TagController {

    private final TagService service;

    @PostMapping
    public ResponseEntity<TagDto> createTag(@RequestBody TagRequest request){
        TagDto tagDto = service.saveTag(request);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/tags/" + tagDto.id()).toUriString());
        return ResponseEntity.created(uri).body(tagDto);
    }

    @PatchMapping("/{id}")
    public TagDto uddateTag(@PathVariable Long id, @RequestBody TagRequest request){
        return service.updateTag(id, request);
    }
    @GetMapping("/type/{type}")
    public List<TagDto> getTagsByType(@PathVariable TagType type) {
        return service.getTagsByType(type);
    }

    @GetMapping("/{id}")
    public TagDto getTagsById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTag(@PathVariable Long id, @RequestParam(defaultValue = "false", name = "force") boolean forceDelete ) {
        service.delete(id, forceDelete);
    }

}
