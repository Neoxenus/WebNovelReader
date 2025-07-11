package com.neoxenus.webnovelreader.chapters.mapper;

import com.neoxenus.webnovelreader.chapters.etitities.Chapter;
import com.neoxenus.webnovelreader.chapters.etitities.dtos.ChapterCreateRequest;
import com.neoxenus.webnovelreader.chapters.etitities.dtos.ChapterDto;
import com.neoxenus.webnovelreader.chapters.etitities.dtos.ChapterUpdateRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ChapterMapper {
    ChapterDto toDto(Chapter chapter);
    Optional<ChapterDto> toDto(Optional<Chapter> chapter);
    List<ChapterDto> toDto(List<Chapter> chapterList);

    Chapter toChapter(ChapterCreateRequest chapterCreateRequest);

    Chapter toChapter(Chapter toUpdate, ChapterUpdateRequest chapterUpdateRequest);
}
