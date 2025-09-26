package com.neoxenus.webnovelreader.chapter.mapper;

import com.neoxenus.webnovelreader.chapter.dto.response.ChapterDtoResponse;
import com.neoxenus.webnovelreader.chapter.dto.response.ChapterSummaryDtoResponse;
import com.neoxenus.webnovelreader.chapter.dto.request.ChapterCreateDtoRequest;
import com.neoxenus.webnovelreader.chapter.dto.request.ChapterUpdateDtoRequest;
import com.neoxenus.webnovelreader.chapter.etitity.Chapter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public interface ChapterMapper {
    ChapterDtoResponse toDto(Chapter chapter);

    Page<ChapterDtoResponse> toDto(Page<Chapter> chapterList);

    Chapter toChapter(ChapterCreateDtoRequest chapterCreateDtoRequest);

    Chapter toChapter(Chapter toUpdate, ChapterUpdateDtoRequest chapterUpdateDtoRequest);

    ChapterSummaryDtoResponse toSummary(Chapter chapter);
    Page<ChapterSummaryDtoResponse> toSummary(Page<Chapter> chapterList);

}
