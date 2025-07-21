package com.neoxenus.webnovelreader.chapter.mapper;

import com.neoxenus.webnovelreader.chapter.etitity.Chapter;
import com.neoxenus.webnovelreader.chapter.dto.request.ChapterCreateRequest;
import com.neoxenus.webnovelreader.chapter.dto.ChapterDto;
import com.neoxenus.webnovelreader.chapter.dto.ChapterSummary;
import com.neoxenus.webnovelreader.chapter.dto.request.ChapterUpdateRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ChapterMapper {
    ChapterDto toDto(Chapter chapter);

    List<ChapterDto> toDto(List<Chapter> chapterList);

    Chapter toChapter(ChapterCreateRequest chapterCreateRequest);

    Chapter toChapter(Chapter toUpdate, ChapterUpdateRequest chapterUpdateRequest);

    ChapterSummary toSummary(Chapter chapter);
    List<ChapterSummary> toSummary(List<Chapter> chapterList);

}
