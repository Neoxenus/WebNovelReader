package com.neoxenus.webnovelreader.tag.controller;


import com.neoxenus.webnovelreader.tag.dto.response.TagDtoResponse;
import com.neoxenus.webnovelreader.tag.dto.request.TagDtoRequest;
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
@RequestMapping("/tags")
@Slf4j
public class TagController {

    private final TagService service;

    @PostMapping
    public ResponseEntity<TagDtoResponse> createTag(@RequestBody TagDtoRequest request){
        TagDtoResponse tagDtoResponse = service.saveTag(request);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/tags/" + tagDtoResponse.id()).toUriString());
        return ResponseEntity.created(uri).body(tagDtoResponse);
    }

    @PatchMapping("/{id}")
    public TagDtoResponse uddateTag(@PathVariable Long id, @RequestBody TagDtoRequest request){
        return service.updateTag(id, request);
    }
    @GetMapping("/type/{type}")
    public List<TagDtoResponse> getTagsByType(@PathVariable TagType type) {
        return service.getTagsByType(type);
    }

    @GetMapping("/{id}")
    public TagDtoResponse getTagsById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTag(@PathVariable Long id, @RequestParam(defaultValue = "false", name = "force") boolean forceDelete ) {
        service.delete(id, forceDelete);
    }

}
