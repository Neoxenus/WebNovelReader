package com.neoxenus.webnovelreader.chapter.mapper;

import com.neoxenus.webnovelreader.chapter.dto.ChapterDto;
import com.neoxenus.webnovelreader.chapter.dto.ChapterSummary;
import com.neoxenus.webnovelreader.chapter.dto.request.ChapterCreateRequest;
import com.neoxenus.webnovelreader.chapter.dto.request.ChapterUpdateRequest;
import com.neoxenus.webnovelreader.chapter.etitity.Chapter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public interface ChapterMapper {
    ChapterDto toDto(Chapter chapter);

    Page<ChapterDto> toDto(Page<Chapter> chapterList);

    Chapter toChapter(ChapterCreateRequest chapterCreateRequest);

    Chapter toChapter(Chapter toUpdate, ChapterUpdateRequest chapterUpdateRequest);

    ChapterSummary toSummary(Chapter chapter);
    Page<ChapterSummary> toSummary(Page<Chapter> chapterList);

}
