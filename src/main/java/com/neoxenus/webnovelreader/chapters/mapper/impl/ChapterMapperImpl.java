package com.neoxenus.webnovelreader.chapters.mapper.impl;

import com.neoxenus.webnovelreader.chapters.etitities.Chapter;
import com.neoxenus.webnovelreader.chapters.etitities.dtos.ChapterCreateRequest;
import com.neoxenus.webnovelreader.chapters.etitities.dtos.ChapterDto;
import com.neoxenus.webnovelreader.chapters.etitities.dtos.ChapterUpdateRequest;
import com.neoxenus.webnovelreader.chapters.mapper.ChapterMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ChapterMapperImpl implements ChapterMapper {
    @Override
    public ChapterDto toDto(Chapter chapter) {
        if (chapter != null) {
            return ChapterDto.builder()
                    .title(chapter.getTitle())
                    .chapterNumber(chapter.getChapterNumber())
                    .content(chapter.getContent())
                    .datePublished(chapter.getDatePublished())
                    .book(chapter.getBook())
                    .build();
        } else {
            return null;
        }
    }

    @Override
    public Optional<ChapterDto> toDto(Optional<Chapter> chapter) {
        return Optional.ofNullable(toDto(chapter.orElse(null)));
    }

    @Override
    public List<ChapterDto> toDto(List<Chapter> chapterList) {
        return chapterList.stream().map(this::toDto).toList();
    }

    @Override
    public Chapter toChapter(ChapterCreateRequest chapterCreateRequest) {
        if (chapterCreateRequest != null) {
            Chapter chapter = new Chapter();
            chapter.setTitle(chapterCreateRequest.getTitle());
            chapter.setChapterNumber(chapterCreateRequest.getChapterNumber());
            chapter.setContent(chapterCreateRequest.getContent());
            return chapter;
        } else {
            return null;
        }

    }

    @Override
    public Chapter toChapter(Chapter toUpdate, ChapterUpdateRequest chapterUpdateRequest) {
        if (toUpdate != null && chapterUpdateRequest != null) {
            toUpdate.setTitle(chapterUpdateRequest.getTitle());
            toUpdate.setChapterNumber(chapterUpdateRequest.getChapterNumber());
            toUpdate.setContent(chapterUpdateRequest.getContent());
            return toUpdate;
        } else {
            return null;
        }
    }
}
