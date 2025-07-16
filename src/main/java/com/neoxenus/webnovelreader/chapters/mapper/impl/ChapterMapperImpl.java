package com.neoxenus.webnovelreader.chapters.mapper.impl;

import com.neoxenus.webnovelreader.chapters.etitities.Chapter;
import com.neoxenus.webnovelreader.chapters.etitities.ChapterContent;
import com.neoxenus.webnovelreader.chapters.etitities.dtos.ChapterCreateRequest;
import com.neoxenus.webnovelreader.chapters.etitities.dtos.ChapterDto;
import com.neoxenus.webnovelreader.chapters.etitities.dtos.ChapterSummary;
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
                    .id(chapter.getId())
                    .title(chapter.getTitle())
                    .chapterNumber(chapter.getChapterNumber())
                    .datePublished(chapter.getDatePublished())
                    .bookId(chapter.getBook() != null ? chapter.getBook().getId() : null)
                    .content(chapter.getContent() != null ? chapter.getContent().getContent() : null)
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

            ChapterContent content = new ChapterContent();
            content.setContent(chapterCreateRequest.getContent());
            content.setChapter(chapter);
            chapter.setContent(content);

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
            toUpdate.getContent().setContent(chapterUpdateRequest.getContent());
            return toUpdate;
        } else {
            return null;
        }
    }

    @Override
    public ChapterSummary toSummary(Chapter chapter) {
        if (chapter != null) {
            return ChapterSummary.builder()
                    .id(chapter.getId())
                    .title(chapter.getTitle())
                    .chapterNumber(chapter.getChapterNumber())
                    .datePublished(chapter.getDatePublished())
                    .build();
        } else {
            return null;
        }

    }

    @Override
    public List<ChapterSummary> toSummary(List<Chapter> chapterList) {
        if(chapterList != null){
            return chapterList.stream().map(this::toSummary).toList();
        } else {
            return null;
        }
    }
}
